package com.assia.model.image;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ImageModel {
    private BigInteger id;
    private String name;
    private String image_Url;

}
