package com.assia.service;

import com.assia.domain.category.Category;
import com.assia.model.category.CategoryForm;
import com.assia.model.category.CategoryModel;
import com.assia.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryModel> getAllCategory(){
        List<CategoryModel> rs = new ArrayList<>();
        this.categoryRepository.findAll().forEach(category -> {
            rs.add(category.toCategory());
        });
        return rs;
    }

    public Optional<Category> getById(BigInteger id){
        return this.categoryRepository.getById(id);
    }
    public void delete(BigInteger id){
        this.getById(id).ifPresent(this.categoryRepository::delete);
    }
    public Optional<com.assia.domain.category.Category> update(BigInteger id, CategoryForm categoryForm){
        return this.getById(id).map(category -> {
            category.setName(categoryForm.getName());
            return this.categoryRepository.save(category);
        });
    }
    public com.assia.domain.category.Category create(CategoryForm categoryForm){
        com.assia.domain.category.Category category = new com.assia.domain.category.Category();
        category.setName(categoryForm.getName());
        return this.categoryRepository.save(category);
    }


}
