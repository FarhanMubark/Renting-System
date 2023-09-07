package com.example.rentingsystem.Service;

import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.MyOrder;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Model.Renter;
import com.example.rentingsystem.Repository.LessorRepository;
import com.example.rentingsystem.Repository.OrderRepository;
import com.example.rentingsystem.Repository.ProductRepository;
import com.example.rentingsystem.Repository.RenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSerivce {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final LessorRepository lessorRepository;
    private final RenterRepository renterRepository;


    public List<MyOrder> getOrders(){
     return orderRepository.findAll();
    }

    public List<MyOrder> getOrder(Integer orderId){
        return orderRepository.findAllOrOrderById(orderId);
    }


    public void addOrder(Product product, Lessor leesor, Renter renter){

        MyOrder myOrder = new MyOrder(null,product.getProductName(),renter.getName(),product.getProductPrice(),leesor.getName(),product.getEndDate());
        orderRepository.save(myOrder);
        MyOrder order = new MyOrder(null,product.getProductName(),renter.getName(),product.getProductPrice(),leesor.getName(),product.getEndDate(),null,null);
        orderRepository.save(order);

    }

}
