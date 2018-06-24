package com.assia.domain.product;

import com.assia.domain.category.Category;
import com.assia.domain.image.Image;
import com.assia.model.image.ImageForm;
import com.assia.model.product.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    private BigDecimal price;
    private BigDecimal size_screen;
    private String color;
    private String made_in;
    private String battery_type;
    private String warranty_period;
    private String created_date;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    public ProductModel toProduct() {
        ProductModel rs = new ProductModel();
        rs.setId(id);
        rs.setName(name);
        rs.setPrice(price);
        rs.setSize_screen(size_screen);
        rs.setColor(color);
        rs.setMade_in(made_in);
        rs.setBattery_type(battery_type);
        rs.setWarranty_period(warranty_period);
        rs.setCreated_date(created_date);
        rs.setCategory(category);
        rs.setImageForms(getImages().stream().map(Image::toImageForm).collect(Collectors.toList()));
        return rs;
    }

    public void setListImage(List<ImageForm> listImageForms){
        images.clear();
        if(!CollectionUtils.isEmpty(listImageForms)){
            for(int i =0;i<listImageForms.size();i++){
                ImageForm imageForm = listImageForms.get(i);
                Image image = new Image();
                image.setProduct(this);
                image.setImage_Url(imageForm.getImage_Url());
                images.add(image);
            }
        }
    }

}
