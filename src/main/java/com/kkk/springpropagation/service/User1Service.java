package com.kkk.springpropagation.service;

import com.kkk.springpropagation.bean.User1;
import com.kkk.springpropagation.dao.User1Dao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface User1Service {
    @Transactional(propagation = Propagation.REQUIRED)
    default void addByRequired(User1 user1) {
        getDao().insert(user1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    default void addByRequiredNew(User1 user1) {
        getDao().insert(user1);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    default void addByMa(User1 user1) {
        getDao().insert(user1);
    }

    @Transactional(propagation = Propagation.NEVER)
    default void addByNever(User1 user1) {
        getDao().insert(user1);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    default void addBySupport(User1 user1) {
        getDao().insert(user1);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    default void addByNotSupport(User1 user1) {
        getDao().insert(user1);
    }
    @Transactional(propagation = Propagation.NESTED)
    default void addByNested(User1 user1) {
        getDao().insert(user1);
    }

    default List<User1> select() {
        return getDao().selectByKey(1);
    }

    default int delete() {
        return getDao().delete();
    }

    User1Dao getDao();

}
