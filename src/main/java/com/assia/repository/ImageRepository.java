package com.assia.repository;

import com.assia.domain.image.Image;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, BigInteger> {
    Optional<Image> getById(BigInteger id);
}
