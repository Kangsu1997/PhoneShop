package com.assia.model.product;

import com.assia.domain.category.Category;
import com.assia.model.image.ImageForm;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
public class ProductModel {
    private BigInteger id;
    private String name;
    private BigDecimal price;
    private BigDecimal size_screen;
    private String color;
    private String made_in;
    private String battery_type;
    private String warranty_period;
    private String created_date;
    private Category category;

    private List<ImageForm> imageForms;
}
