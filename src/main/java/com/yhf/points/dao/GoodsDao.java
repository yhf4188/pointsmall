package com.yhf.points.dao;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.yhf.points.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
            @Result(property = "goods_browse", column = "goods_browse")
    })
    List<Goods> getAll();

    @Select("select * from goods where goods_name like CONCAT('%',#{name},'%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "goods_name"),
            @Result(property = "points", column = "goods_points"),
            @Result(property = "picture", column = "goods_pic"),
            @Result(property = "goods_num", column = "goods_num"),
            @Result(property = "goods_browse", column = "goods_browse")
    })
    List<Goods> getFruzz(String name);

    @Select("select * from goods where goods_point between #{min} and #{max}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "goods_name"),
            @Result(property = "points", column = "goods_points"),
            @Result(property = "picture", column = "goods_pic"),
            @Result(property = "goods_num", column = "goods_num"),
            @Result(property = "goods_browse", column = "goods_browse")
    })
    List<Goods> getByPoints(int min,int max);
}
