package com.assia.model.image;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;

@Data
public class ImageForm {
    @NotBlank
    private String image_Url;

}
