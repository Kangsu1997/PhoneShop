package com.assia.model.order;

import lombok.Data;

import java.math.BigInteger;

@Data
public class OrderModel {
    private BigInteger id;
    private String name;
    private String order_date;
}
