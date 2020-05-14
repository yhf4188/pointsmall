package com.yhf.points.model;

import lombok.Data;

@Data
//商品类
public class Goods {
    int id;//id
    String name;//商品名
    int points;//商品积分
    String picture;//商品图片地址
    int goods_num;
    int goods_browse;

    public Goods() {
    }

    public Goods(int id, String name, int points, String picture, int goods_num, int goods_browse) {
        this.id=id;
        this.name = name;
        this.points = points;
        this.picture = picture;
        this.goods_num = goods_num;
        this.goods_browse = goods_browse;
    }
}
