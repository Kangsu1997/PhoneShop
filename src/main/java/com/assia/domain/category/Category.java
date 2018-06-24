package com.assia.domain.category;

import com.assia.domain.product.Product;
import com.assia.model.category.CategoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList = new ArrayList<>();

    public CategoryModel toCategory(){
        CategoryModel rs = new CategoryModel();
        rs.setId(id);
        rs.setName(name);
        return rs;
    }
}
