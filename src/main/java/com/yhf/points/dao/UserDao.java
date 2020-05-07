package com.yhf.points.dao;

import com.yhf.points.model.Attribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from attribute where username=#{username} and password=#{password} and email=#{email} and phone=#{phone}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "points", column = "points"),
    })
    public Attribute getUser(Attribute user);
}
