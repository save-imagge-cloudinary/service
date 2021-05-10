package com.example.service.repository;

import com.example.service.entity.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image,Integer> {
}
