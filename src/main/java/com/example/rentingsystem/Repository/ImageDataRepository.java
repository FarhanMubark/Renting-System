package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDataRepository extends JpaRepository<ImageData, Integer> {
    ImageData findByName(String name);
}
