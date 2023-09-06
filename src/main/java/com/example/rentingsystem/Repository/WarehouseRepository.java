package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    Warehouse findWarehouseById(Integer id);
}
