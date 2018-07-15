package com.kkk.springpropagation.service;

import com.kkk.springpropagation.bean.User2;
import com.kkk.springpropagation.dao.User2Dao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface User2Service {
    @Transactional(propagation = Propagation.REQUIRED)
    default void addByRequired(User2 user) {
        getDao().insert(user);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    default void addByRequiredEx(User2 user) {
        getDao().insert(user);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    default void addByRequiredNew(User2 user) {
        getDao().insert(user);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    default void addByRequiredNewEx(User2 user) {
        getDao().insert(user);
        throw new RuntimeException();
    }
    @Transactional(propagation = Propagation.MANDATORY)
    default void addByMa(User2 user) {
        getDao().insert(user);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    default void addByMaEx(User2 user) {
        getDao().insert(user);
        throw new RuntimeException();
    }
    @Transactional(propagation = Propagation.NEVER)
    default void addByNever(User2 user) {
        getDao().insert(user);
    }
    @Transactional(propagation = Propagation.NEVER)
    default void addByNeverEx(User2 user) {
        getDao().insert(user);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    default void addBySupport(User2 user) {
        getDao().insert(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    default void addBySupportEx(User2 user) {
        getDao().insert(user);
        throw new RuntimeException();
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    default void addByNotSupport(User2 user) {
        getDao().insert(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    default void addByNOtSupportEx(User2 user) {
        getDao().insert(user);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    default void addByNested(User2 user) {
        getDao().insert(user);
    }

    @Transactional(propagation = Propagation.NESTED)
    default void addByNestedEx(User2 user) {
        getDao().insert(user);
        throw new RuntimeException();
    }

    default List<User2> select() {
        return getDao().selectByKey(1);
    }

    default int delete() {
        return getDao().delete();
    }

    User2Dao getDao();

}
