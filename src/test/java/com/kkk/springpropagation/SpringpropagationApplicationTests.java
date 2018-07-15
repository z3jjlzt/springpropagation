package com.kkk.springpropagation;

import com.kkk.springpropagation.controller.TestController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringpropagationApplication.class)
public class SpringpropagationApplicationTests {

    @Autowired
    TestController controller;

    @Test
    public void contextLoads() {
    }

    @Test
    public void TestNotransaction_exception_required_required() {
        controller.notransaction_exception_required_required();
    }

    @Test
    public void TestNotransaction_required_required_exception() {
        controller.notransaction__required_required_exception();
    }

    @Test
    public void TestTransaction_exception_required_required() {
        controller.transaction_exception_required_required();
    }

    @Test
    public void TestTransaction_required_required_exception() {
        controller.transaction_required_required_ex();
    }
    @Test
    public void TestTransaction_required_required_exception_try() {
        controller.transaction_required_required_ex_try();
    }

    @Test
    public void TestNOtransaction_exception_requirednew_requirednew() {
        controller.notransaction_exception_requirednew_requirednew();
    }

    @Test
    public void TestNOtransaction_requirednew_requirednew_exception() {
        controller.notransaction_requirednew_requirednew_ex();
    }

    @Test
    public void TestNOtransaction_requirednew_requirednew_exception_try() {
        controller.notransaction_requirednew_requirednew_ex_try();
    }

    @Test
    public void TestTransaction_exception_requirednew_requirednew() {
        controller.transaction_exception_requirednew_requirednew();
    }
    @Test
    public void TestTransaction_requirednew_requirednew_exception() {
        controller.transaction_requirednew_requirednew_ex();
    }
    @Test
    public void TestTransaction_requirednew_requirednew_exception_try() {
        controller.transaction_requirednew_requirednew_ex_try();
    }

    @Test
    public void TestNotransaction_ma_ma() {
        controller.notransaction_ma_ma();
    }

    @Test
    public void TestTransaction_ma_ma() {
        controller.transaction_ma_ma();
    }
    @Test
    public void TestNotransaction_never_never() {
        controller.notransaction_never_never();
    }

    @Test
    public void TestNotransaction_su_su() {
        controller.notransaction_support_support();
    }

    @Test
    public void TestTransaction_ex_su_su() {
        controller.transaction_exception_support_support();
    }

    @Test
    public void TestTransaction_ex_notsu_notsu() {
        controller.transaction_exception_notsupport_notsupport();
    }

    @Test
    public void TestTransaction_ex_nested_nested() {
        controller.notransaction_exception_nested_nested();
    }

    @Test
    public void TestTransaction_nested_nested_ex() {
        controller.transaction_nested_nested_ex();
    }

    @Before
    public void init() {
        controller.init();
    }


    @After
    public void print() {
        controller.selectAll();
    }


}
