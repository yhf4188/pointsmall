package com.yhf.points.model;

import lombok.Data;

@Data
public class Attribute {
    int id;
    String username;
    String password;
    String email;
    String phone;
    String points;
    String vip;

    public Attribute(int id, String username, String password, String email, String phone, String points, String vip) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.points = points;
        this.vip = vip;
    }

    public Attribute() {
    }
}
