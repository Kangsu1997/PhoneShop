package com.assia.service;

import com.assia.domain.category.Category;
import com.assia.domain.category.Category_;
import com.assia.domain.product.Product;
import com.assia.domain.product.Product_;
import com.assia.domain.user.PagingObject;
import com.assia.model.image.ImageForm;
import com.assia.model.product.ProductForm;
import com.assia.model.product.ProductModel;
import com.assia.repository.ImageRepository;
import com.assia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.assia.domain.product.Product_.price;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ImageRepository imageRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.imageRepository = imageRepository;
    }
    public List<ProductModel> getAllProduct(){
        List<ProductModel> rs = new ArrayList<>();
        this.productRepository.findAll().forEach(product -> {
            rs.add(product.toProduct());
        });
        return rs;
    }

    public Optional<Product> getById(BigInteger id){
        return this.productRepository.getById(id);
    }
    public void delete(BigInteger id){
        this.getById(id).ifPresent(this.productRepository::delete);
    }
    public com.assia.domain.product.Product create(ProductForm productForm){
        com.assia.domain.product.Product product = new com.assia.domain.product.Product();
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setSize_screen(productForm.getSize_screen());
        product.setColor(productForm.getColor());
        product.setMade_in(productForm.getMade_in());
        product.setBattery_type(productForm.getBattery_type());
        product.setWarranty_period(productForm.getWarranty_period());
        product.setCreated_date(productForm.getCreated_date());
        product.setCategory(productForm.getCategory());
        product.setListImage(productForm.getImageForms());
        return this.productRepository.save(product);
    }

    public Optional<com.assia.domain.product.Product> update(BigInteger id, ProductForm productForm) {
        return this.getById(id).map(product -> {
            product.setName(productForm.getName());
            product.setPrice(productForm.getPrice());
            product.setColor(productForm.getColor());

            product.setCategory(productForm.getCategory());

            product.getImages().clear();
            if (productForm.getImageForms() != null) {
                for (ImageForm imageForm : productForm.getImageForms()) {
                    com.assia.domain.image.Image image = imageRepository.findOne(id);
                    image.setProduct(product);
                    image.setImage_Url(imageForm.getImage_Url());
                    product.getImages().add(image);
                }
            }
            System.out.println(product.getImages().size());
            return this.productRepository.save(product);
        });
    }
    public List<com.assia.domain.product.Product> getAllCategory(BigInteger categoryId){
        List<com.assia.domain.product.Product> rs = new ArrayList<>();
        Category category = categoryService.getById(categoryId).orElse(null);
        if(category != null){
            rs = this.productRepository.getAllCategory(category);
        }

        return rs;
    }

    public PagingObject<ProductModel> getAllProducts (Pageable pageable, String name, BigDecimal fistPrice, BigDecimal lastPrice , BigInteger id){
        if (pageable.getPageSize() >1000) throw new RuntimeException("Page size too big");
        PagingObject<ProductModel> rs = new PagingObject<>();
        Page<com.assia.domain.product.Product> productPage;
        if(StringUtils.hasText(name) || StringUtils.isEmpty(fistPrice) || StringUtils.isEmpty(lastPrice) || StringUtils.isEmpty(id)){
            productPage = productRepository.findAll((root, query, cb) -> {
                List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasText(name)) {
                    predicates.add(cb.like(root.get(Product_.name), "%" + name + "%"));
                }
                if (fistPrice!=null && lastPrice!= null) {
                    final javax.persistence.criteria.Predicate pricePredicate;
                    pricePredicate = cb.and(
                            cb.greaterThanOrEqualTo(root.get(price),fistPrice),
                            cb.lessThanOrEqualTo(root.get(price),lastPrice));
                    predicates.add(pricePredicate);
                }

                if(id != null) {
                    final Join<com.assia.domain.product.Product, Category> categoryJoin = root.join(Product_.category);
                    predicates.add(cb.equal(categoryJoin.get(Category_.id), id));
                }
                return cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[predicates.size()]));
            }, pageable);
        }else {
            productPage = productRepository.findAll(pageable);
        }
        rs.setTotal(productPage.getTotalElements());
        rs.setTotalPage(productPage.getTotalPages());
        rs.setData(productPage.getContent().stream().map(com.assia.domain.product.Product::toProduct).collect(Collectors.toList()));

        return rs;
    }

}