package com.assia.model.order;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;

@Data
public class OrderForm {
    @NotBlank
    private BigInteger id;
    @NotBlank
    private String name;
    @NotBlank
    private String order_date;

}
