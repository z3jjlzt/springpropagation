package com.kkk.springpropagation.service.impl;

import com.kkk.springpropagation.dao.User2Dao;
import com.kkk.springpropagation.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User2ServiceImpl implements User2Service {
    @Autowired
    private User2Dao dao;

    @Override
    public User2Dao getDao() {
        return dao;
    }
}
