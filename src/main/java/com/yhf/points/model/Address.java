package com.yhf.points.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Address {
    private int id;
    private int attribute_id;
    private String address;
    private boolean ad_default;

    public Address() {
    }

    public Address(int id, int attribute_id, String address,boolean ad_default) {
        this.id = id;
        this.attribute_id = attribute_id;
        this.address = address;
        this.ad_default = ad_default;
    }
}
