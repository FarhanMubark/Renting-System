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

import java.time.LocalDateTime;
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


    public void addOrder(Product product, Renter renter,Integer quantity ,LocalDateTime orderDate,Integer totalHours ){

        Integer pricePerHour = quantity * product.getProductPrice();
        double finalPrice = quantity * product.getProductPrice() * totalHours;

        // isreturend if he still didn't return the products, so he will get warning
        boolean isreturned = false;
        MyOrder order = new MyOrder(null,product.getLessor().getUser().getUsername(),product.getProductName(),product.getQuantity()
                ,pricePerHour,product.getProductPrice(),totalHours,finalPrice,orderDate,renter.getUser().getUsername(),renter.getPhoneNumber(),isreturned,true,false,null,product,renter);

        product.setQuantity(product.getQuantity()-quantity);
        Lessor lessor = product.getLessor();
        lessor.setBalance(finalPrice);
        productRepository.save(product);
        orderRepository.save(order);



    }

}
