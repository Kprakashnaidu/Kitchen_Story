package com.kitchenstory.service;

import com.kitchenstory.entity.ImageEntity;
import com.kitchenstory.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Transactional
    public ImageEntity save(ImageEntity image) {
        return repository.save(image);
    }

    @Transactional
    public List<ImageEntity> saveAll(List<ImageEntity> images) {
        return repository.saveAll(images);
    }

    @Transactional
    public ImageEntity findById(Integer id) {
        return repository.findById(id).get();
    }

    @Transactional
    public List<ImageEntity> findAll() {
        return repository.findAll();
    }
}
