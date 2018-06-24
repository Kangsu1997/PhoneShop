package com.assia.controller;

import com.assia.domain.user.PagingObject;
import com.assia.exception.NotFoundException;
import com.assia.model.product.ProductModel;
import com.assia.model.product.ProductForm;
import com.assia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
@RequestMapping(AbstractController.API + "/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public PagingObject<ProductModel> getAllProduct(Pageable pageable,
                                                    @RequestParam(required = false, defaultValue = "") String name,
                                                    @RequestParam(required = false, defaultValue = "") BigDecimal fistPrice,
                                                    @RequestParam(required = false, defaultValue = "") BigDecimal lastPrice,
                                                    @RequestParam(required = false, defaultValue = "") BigInteger id,
                                                    @RequestParam(required = false, defaultValue = "") BigInteger categories_id) {
        return productService.getAllProducts(pageable, name, fistPrice, lastPrice, id);
    }

    @RequestMapping(value ="/{id}" ,method = RequestMethod.GET)
    public ProductModel getById(@PathVariable("id") BigInteger id){
        return this.productService.getById(id).map(com.assia.domain.product.Product::toProduct).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id){
        this.productService.delete(id);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductModel update(@PathVariable("id") BigInteger id, @Valid @RequestBody ProductForm productForm){
        return this.productService.update(id,productForm).map(com.assia.domain.product.Product::toProduct).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductModel insert(@RequestBody ProductForm productForm){
        return this.productService.create(productForm).toProduct();
    }

}
