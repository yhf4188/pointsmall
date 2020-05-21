package com.yhf.points.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yhf.points.model.Attribute;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
            @Result(property = "address", column = "id",many = @Many(select = "com.yhf.points.dao.AddressDao.getAddressByAttribute",fetchType= FetchType.LAZY))
    })
    public Attribute getUser(Attribute user);

    @Select("select id from attribute where username=#{username}")
    public Integer getID(String username);

    @Select("select * from attribute where username=#{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "points", column = "points"),
    })
    public Attribute getUserByName(String username);

    @Update("update attribute set points=#{points} where id=#{id}")
    public int updatePoints(Attribute user);
}
