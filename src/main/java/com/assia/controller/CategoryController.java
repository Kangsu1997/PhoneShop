package com.assia.controller;

import com.assia.exception.NotFoundException;
import com.assia.model.category.CategoryForm;
import com.assia.model.category.CategoryModel;
import com.assia.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryModel> getAll(){
        return this.categoryService.getAllCategory();
    }
    @RequestMapping(value ="/{id}" ,method = RequestMethod.GET)
    public CategoryModel getById(@PathVariable("id") BigInteger id){
        return this.categoryService.getById(id).map(com.assia.domain.category.Category::toCategory).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id){
        this.categoryService.delete(id);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryModel update(@PathVariable("id") BigInteger id, @Valid @RequestBody CategoryForm categoryForm){
        return this.categoryService.update(id,categoryForm).map(com.assia.domain.category.Category::toCategory).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryModel insert(@Valid @RequestBody CategoryForm categoryForm){
        return this.categoryService.create(categoryForm).toCategory();
    }
}
