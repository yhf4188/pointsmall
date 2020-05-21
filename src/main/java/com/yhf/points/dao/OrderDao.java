package com.yhf.points.dao;

import com.yhf.points.model.Attribute;
import com.yhf.points.model.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderDao {
    @Select(value = "select * from good_order where user_id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "goods_id", column = "goods_id"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "goods_state", column = "goods_state"),
            @Result(property = "address",column = "address_id",one = @One(select = "com.yhf.points.dao.AddressDao.get",fetchType= FetchType.EAGER))
    })
    public List<Order> getAllOrderByUser(Attribute user);

    @Update(value = "update good_order set goods_state = #{goods_state} where id={id}")
    public int updateOrderState(Order order);

    @Delete(value = "delete good_order where id = #{id}")
    public int deleteOrder(Order order);

    @Insert(value = "insert into good_order (goods_id,user_id,goods_state,address_id)  values(#{order.goods_id},#{order.user_id},#{order.goods_state},#{address_id} )")
    public int insertOrder(Order order, Integer address_id);

}
