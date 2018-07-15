package com.kkk.springpropagation.dao;

import com.kkk.springpropagation.bean.User2;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface User2Dao {

    @Insert("insert into  user2(name) value(#{name})")
    int insert(User2 user);

    @Select("select * from user2")
    List<User2> selectByKey(Integer user);

    @Delete("delete from user2")
    int delete();
}
