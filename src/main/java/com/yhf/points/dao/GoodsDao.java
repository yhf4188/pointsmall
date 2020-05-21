package com.yhf.points.dao;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.yhf.points.model.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author yhf
 * 商品查询DAO
 */
@Mapper
public interface GoodsDao {
    @Select("select * from goods")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "goods_name"),
            @Result(property = "points", column = "goods_points"),
            @Result(property = "picture", column = "goods_pic"),
            @Result(property = "goods_num", column = "goods_num"),
            @Result(property = "goods_browse", column = "goods_browse"),
            @Result(property = "goods_classf", column = "goods_classf")
    })
    List<Goods> getAll();

    @Select("select * from goods where goods_name like CONCAT('%',#{name},'%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "goods_name"),
            @Result(property = "points", column = "goods_points"),
            @Result(property = "picture", column = "goods_pic"),
            @Result(property = "goods_num", column = "goods_num"),
            @Result(property = "goods_browse", column = "goods_browse"),
            @Result(property = "goods_classf", column = "goods_classf")
    })
    List<Goods> getFruzz(String name);

    @Select("select * from goods where goods_point between #{min} and #{max}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "goods_name"),
            @Result(property = "points", column = "goods_points"),
            @Result(property = "picture", column = "goods_pic"),
            @Result(property = "goods_num", column = "goods_num"),
            @Result(property = "goods_browse", column = "goods_browse"),
            @Result(property = "goods_classf", column = "goods_classf")
    })
    List<Goods> getByPoints(int min,int max);

    @Select("select * from goods where id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "goods_name"),
            @Result(property = "points", column = "goods_points"),
            @Result(property = "picture", column = "goods_pic"),
            @Result(property = "goods_num", column = "goods_num"),
            @Result(property = "goods_browse", column = "goods_browse"),
            @Result(property = "goods_classf", column = "goods_classf")

    })
    Goods getGood(int id);

    @Update("update goods set goods_num=#{goods_num} where id=#{id} and goods_num>0")
    int updateNum(Goods goods);
}
