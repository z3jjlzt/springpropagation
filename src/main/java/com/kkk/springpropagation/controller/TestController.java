package com.kkk.springpropagation.controller;


import com.kkk.springpropagation.bean.User1;
import com.kkk.springpropagation.bean.User2;
import com.kkk.springpropagation.service.User1Service;
import com.kkk.springpropagation.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Controller
public class TestController {

    @Autowired
    User1Service user1Service;

    @Autowired
    User2Service user2Service;

    /**
     * 外围方法不开启事务，张三李四均插入成功，插入的两个事务分别在
     * 自己的事务中独立运行，外围方法抛出异常不影响内部的事务，不会
     * 造成内部事务的回滚。
     */
    public void notransaction_exception_required_required(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequired(user2);

        throw new RuntimeException();
    }

    /**
     * 外围方法不开启事务，张三插入成功，李四插入失败，插入的两个事务分别在
     * 自己的事务中独立运行，插入李四的事务中抛出异常,造成内部事务的回滚。
     */
    public void notransaction__required_required_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequiredEx(user2);

    }


    /**
     * 外围方法开启事务，张三李四均未插入成功，内部两个方法加入外围
     * 事务中，外务事务回滚，内部方法也要回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequired(user2);

        throw new RuntimeException();
    }


    /**
     * 外围方法开启事务，张三李四均未插入成功，内部两个方法加入外围
     * 事务中，内部方法抛出异常回滚，外围事务感知到异常使得整个事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_ex(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequiredEx(user2);
    }

    /**
     * 外围方法开启事务，张三李四均未插入成功，内部两个方法加入外围
     * 事务中，内部方法抛出异常回滚，即使内部方法捕获到异常不被外围方法感知
     * 整个事务依旧回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_ex_try(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        try {
            user2Service.addByRequiredEx(user2);
        } catch (Exception e) {
            System.out.println("内部方法异常");
        }
    }

    /**
     * 外围方法不开启事务，张三李四均插入成功，内部两个方法分别在自己独立的
     * 事务中，外围方法抛出异常不会导致内部事务回滚。
     */
    public void notransaction_exception_requirednew_requirednew(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequiredNew(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequiredNew(user2);
        throw new RuntimeException();
    }

    /**
     * 外围方法不开启事务，张三插入成功，李四擦入失败，内部两个方法分别在自己独立的
     * 事务中，插入李四的事务抛出异常回滚，其他事务不受影响。
     */
    public void notransaction_requirednew_requirednew_ex(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequiredNew(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequiredNewEx(user2);
    }

    /**
     * 外围方法不开启事务，张三插入成功，李四擦入失败，内部两个方法分别在自己独立的
     * 事务中，插入李四的事务抛出异常并在外围方法捕获，该事务依旧回滚，其他事务不受影响。
     */
    public void notransaction_requirednew_requirednew_ex_try(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequiredNew(user1);

        User2 user2=new User2();
        user2.setName("李四");
        try {
            user2Service.addByRequiredNewEx(user2);
        } catch (Exception e) {
            System.out.println("内部方法异常");
        }
    }

    /**
     * 外围方法开启事务，插入张三的方法和外务方法在同一个事务，另外两个
     * 方法分别在各自独立的新建事务中，外围方法抛出异常只回滚和外围方法处在
     * 同一个事务的方法。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_requirednew_requirednew(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequiredNew(user2);

        User2 user3=new User2();
        user3.setName("王五");
        user2Service.addByRequiredNew(user3);
        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务，插入张三的方法和外围方法在同一个事务，
     * 另外两个方法分别在各自开启的新建事务中，由于插入王五的事务
     * 抛出异常，所以该事务回滚，同时异常被外围方法感知，导致外围
     * 事务也回滚，所以插入张三的方法也回滚，插入李四的事务不受影响。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_requirednew_requirednew_ex(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequiredNew(user2);

        User2 user3=new User2();
        user3.setName("王五");
        user2Service.addByRequiredNewEx(user3);
    }

    /**
     * 外围方法开启事务，插入张三的方法和外围方法在同一个事务，
     * 另外两个方法分别在各自开启的新建事务中，由于插入王五的事务
     * 抛出异常，所以该事务回滚，同时异常被外围方法感知并捕获，
     * 所以插入张三的方法不回滚，插入李四的事务不受影响。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_requirednew_requirednew_ex_try(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByRequiredNew(user2);

        User2 user3=new User2();
        user3.setName("王五");
        try {
            user2Service.addByRequiredNewEx(user3);
        } catch (Exception e) {
            System.out.println("内部方法异常");
        }
    }

    /**
     * 外围方法未开启事务，直接抛出
     * IllegalTransactionStateException:
     * No existing transaction found for transaction marked with propagation 'mandatory'
     */
    public void notransaction_ma_ma(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByMa(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByMa(user2);
    }

    /**
     * 外围方法开启事务，无论是外围方法抛出异常，
     * 还是内部方法抛出异常，或者内部方法抛出异常且被
     * 外部方法捕获，都会导致整个事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_ma_ma(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByMa(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByMa(user2);

//        throw new RuntimeException();
//        try {
//            user2Service.addByMaEx(user2);
//        } catch (Exception e) {
//            System.out.println("内部方法异常");
//        }
    }

    /**
     * 外围方法开启事务，则抛出IllegalTransactionStateException:
     * Existing transaction found for transaction marked with propagation 'never'
     */
//    @Transactional(propagation = Propagation.REQUIRED)
    public void notransaction_never_never(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByNever(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByNever(user2);
    }

    /**
     * 外围方法未开启事务，抛出异常不会回滚
     */
    public void notransaction_support_support(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addBySupport(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addBySupport(user2);

//        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务，不论是外围方法抛异常，
     * 还是内部方法抛异常，抑或是内部方法的异常
     * 被外围方法感知并捕获，都会导致整个事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_support_support(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addBySupport(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addBySupport(user2);

//        try {
//            user2Service.addBySupportEx(user2);
//        } catch (Exception e) {
//            System.out.println("内部方法异常");
//        }

//        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务，
     * 1. 外围方法抛出异常，插入张三的事务回滚，另外两个插入成功。
     * 2. 内部方法抛出异常，且被外围方法感知，另外两个插入成功。
     * 3. 内部方法抛出异常，且被外围方法感知和捕获，三个插入都成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_notsupport_notsupport(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addByNotSupport(user2);

        User2 user3=new User2();
        user3.setName("王五");
//        user2Service.addByNotSupport(user3);1
//        throw new RuntimeException(); 1

//        user2Service.addByNOtSupportEx(user3); 2

//        try { 3
//            user2Service.addByNOtSupportEx(user3);
//        } catch (Exception e) {
//            System.out.println("内部方法异常");
//        }

    }

    /**
     * 外围方法未开启事务，nested和required类似，
     * 1. 三个内部方法均在自己的事务中独立运行，外围方法异常不影响内部方法，均插入成功。
     * 2. 张三李四插入成功，王五插入失败。
     * 3. 同2.
     */
    public void notransaction_exception_nested_nested() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addByNested(user2);

        User2 user3 = new User2();
        user3.setName("王五");

//        user2Service.addByNested(user3);1
//        throw new RuntimeException();1

//        user2Service.addByNestedEx(user3);2

//        try { 3
//            user2Service.addByNestedEx(user3);
//        } catch (Exception e) {
//        }
    }

    /**
     * 外围方法开启事务，
     * 1. 外围方法抛出异常，外围事务回滚，导致嵌套事务也回滚。3个均插入失败。
     * 2. 内部方法抛出异常，且被外围方法感知，导致整个事务抛出异常。3个均插入失败。
     * 3. 内部方法抛出异常，且被外围方法感知并捕获，只对单个子事务回滚，张三李四插入成功，王五插入失败。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_ex() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addByRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addByNested(user2);

        User2 user3 = new User2();
        user3.setName("王五");

//        user2Service.addByNested(user3);1
//        throw new RuntimeException();1

//        user2Service.addByNestedEx(user3);2

//        try { 3
//            user2Service.addByNestedEx(user3);
//        } catch (Exception e) {
//        }
    }

    public void init() {
        System.out.println("重置" + user1Service.delete());
        System.out.println("重置" + user2Service.delete());
    }

    public void selectAll() {
        System.out.println(user1Service.select());
        System.out.println(user2Service.select());
    }

}
