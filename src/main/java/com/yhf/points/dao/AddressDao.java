package com.yhf.points.dao;


import com.yhf.points.model.Address;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from address where attribute_id=#{attribute_id} and address=#{address} limit 1")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "attribute_id",property = "attribute_id"),
            @Result(column = "address",property = "address"),
            @Result(column = "ad_default",property = "ad_default")
    })
    public Address getByAttrIDAndAddress(Address address);

    @Insert("insert into address(address,attribute_id) values(#{address},#{attribute_id})")
    public int setAddress(Address address);

    @Update("update address set ad_default = 1 where id=#{id} and attribute_id=#{attribute_id}")
    public int setDefault(int id,int attribute_id);

    @Update("update address set ad_default = 0 where id!=#{id} and attribute_id=#{attribute_id}")
    public int setUnDefault(int id,int attribute_id);

}
