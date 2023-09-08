package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<MyOrder,Integer> {
    MyOrder findOrderById(Integer id);

    List<MyOrder>findAllOrOrderById(Integer id);

    @Query("select o from MyOrder o where o.orderIsActive = true ")
    List<MyOrder>getMyOrdersByOrderIsActive();

    @Query("select o from MyOrder o where o.orderBlockState = true")
    List<MyOrder>getMyOrdersByOrderBlockState();
}
