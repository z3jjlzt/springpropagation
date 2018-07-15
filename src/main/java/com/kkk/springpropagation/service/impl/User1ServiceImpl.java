package com.kkk.springpropagation.service.impl;

import com.kkk.springpropagation.dao.User1Dao;
import com.kkk.springpropagation.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User1ServiceImpl implements User1Service {
    @Autowired
    private User1Dao dao;

    @Override
    public User1Dao getDao() {
        return dao;
    }
}
