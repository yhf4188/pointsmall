package com.yhf.points.dao;


import com.yhf.points.model.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressDao {
    @Select("select * from address where attribute_id=#{attribute_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "attribute_id",property = "attribute_id"),
            @Result(column = "address",property = "address"),
            @Result(column = "ad_default",property = "ad_default")
    })
    public List<Address> getAddressByAttribute(String attribute_id);

    @Select("select * from address where id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "attribute_id",property = "attribute_id"),
            @Result(column = "address",property = "address"),
            @Result(column = "ad_default",property = "ad_default")
    })
    public Address get(String id);
}
