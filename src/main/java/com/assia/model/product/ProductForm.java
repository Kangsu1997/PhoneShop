package com.assia.model.product;

import com.assia.domain.category.Category;
import com.assia.model.image.ImageForm;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductForm {
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal size_screen;
    @NotNull
    private String color;
    @NotBlank
    private String made_in;
    @NotBlank
    private String battery_type;
    @NotBlank
    private String warranty_period;
    @NotBlank
    private String created_date;
    @NotNull
    private Category category;
    @NotEmpty
    private List<ImageForm> imageForms;


}
