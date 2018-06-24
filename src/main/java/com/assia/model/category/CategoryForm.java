package com.assia.model.category;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;

public class CategoryForm {
    @NotBlank
    private BigInteger id;
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }
}
