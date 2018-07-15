package com.kkk.springpropagation.dao;

import com.kkk.springpropagation.bean.User1;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface User1Dao {
    @Insert("insert into  user1(name) value(#{name})")
    int insert(User1 user);

    @Select("select * from user1")
    List<User1> selectByKey(Integer user);

    @Delete("delete from user1")
    int delete();
}
