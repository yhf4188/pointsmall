package com.yhf.points.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Attribute {
    int id;
    String username;
    String password;
    String email;
    String phone;
    int points;
    List<Address> address;

    public Attribute(int id, String username, String password, String email, String phone, int points,  List<Address> address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.points = points;
        this.address = address;
    }

    public Attribute() {

    }
}
