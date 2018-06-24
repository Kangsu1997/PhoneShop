package com.assia.controller;

import com.assia.exception.NotFoundException;
import com.assia.model.image.ImageForm;
import com.assia.model.image.ImageModel;
import com.assia.service.ImageSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

import java.util.List;
@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageSevice imageSevice;
    @RequestMapping(method = RequestMethod.GET)
    public List<ImageModel> getAll(){
        return this.imageSevice.getAllImage();
    }
    @RequestMapping(value ="/{id}" ,method = RequestMethod.GET)
    public ImageModel getById(@PathVariable("id") BigInteger id){
        return this.imageSevice.getById(id).map(com.assia.domain.image.Image::toImage).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id){
        this.imageSevice.delete(id);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ImageModel update(@PathVariable("id") BigInteger id, @Valid @RequestBody ImageForm imageForm){
        return this.imageSevice.update(id,imageForm).map(com.assia.domain.image.Image::toImage).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ImageModel insert(@Valid @RequestBody ImageForm imageForm){
        return this.imageSevice.create(imageForm).toImage();
    }
}
