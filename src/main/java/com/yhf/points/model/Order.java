package com.yhf.points.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
//订单类
public class Order {
    int id;
    int goods_id;
    int user_id;
    String goods_state;
    Address address;

    public Order() {
    }

    public Order(int id, int goods_id, int user_id, String goods_state) {
        this.id = id;
        this.goods_id = goods_id;
        this.user_id = user_id;
        this.goods_state = goods_state;
    }

    public Order(int id, int goods_id, int user_id, String goods_state, Address address) {
        this.id = id;
        this.goods_id = goods_id;
        this.user_id = user_id;
        this.goods_state = goods_state;
        this.address = address;
    }
}
