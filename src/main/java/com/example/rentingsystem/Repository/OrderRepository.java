package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findOrderById(Integer id);

    List<Order>findAllOrOrderById(Integer id);



}
