package com.assia.domain.image;

import com.assia.domain.product.Product;
import com.assia.model.image.ImageForm;
import com.assia.model.image.ImageModel;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
   private String name;
    private String image_Url;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

    public ImageModel toImage() {
        ImageModel rs = new ImageModel();
        rs.setId(id);
        rs.setName(name);
        rs.setImage_Url(image_Url);
        return rs;
    }

   public ImageForm toImageForm(){
       ImageForm rs = new ImageForm();
       rs.setImage_Url(image_Url);
       return rs;
   }
}
