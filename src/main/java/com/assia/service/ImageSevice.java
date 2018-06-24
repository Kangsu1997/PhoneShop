package com.assia.service;

import com.assia.model.image.ImageForm;
import com.assia.model.image.ImageModel;
import com.assia.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageSevice {
    private final ImageRepository imageRepository;
    @Autowired
    public ImageSevice(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public List<ImageModel> getAllImage(){
        List<ImageModel> rs = new ArrayList<>();
        this.imageRepository.findAll().forEach(image -> {
            rs.add(image.toImage());
        });
        return rs;
    }

    public Optional<com.assia.domain.image.Image> getById(BigInteger id){
        return this.imageRepository.getById(id);
    }
    public void delete(BigInteger id){
        this.getById(id).ifPresent(this.imageRepository::delete);
    }
    public Optional<com.assia.domain.image.Image> update(BigInteger id, ImageForm imageForm){
        return this.getById(id).map(image -> {
            return this.imageRepository.save(image);
        });
    }
    public com.assia.domain.image.Image create(ImageForm imageForm){
        com.assia.domain.image.Image image = new com.assia.domain.image.Image();
        image.setName(image.getName());
        return this.imageRepository.save(image);
    }
}


