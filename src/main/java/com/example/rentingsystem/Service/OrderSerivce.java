package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
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

import java.time.Duration;
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
        Double finalPrice =(double) quantity * product.getProductPrice() * totalHours;

        // isreturend if he still didn't return the products, so he will get warning
        boolean isreturned = false;
        MyOrder order = new MyOrder(null,"In Progress",product.getLessor().getUser().getUsername(),product.getProductName(),quantity
                ,pricePerHour,product.getProductPrice(),totalHours,finalPrice,orderDate,renter.getUser().getUsername(),renter.getPhoneNumber(),isreturned,true,false,null,product,renter);

        if(product.getQuantity() <= 0) {
            product.setProductStatus("Out of Stock");
        }
        product.setQuantity(product.getQuantity()-quantity);
        Lessor lessor = product.getLessor();
        lessor.setBalance(lessor.getBalance()+finalPrice);
        productRepository.save(product);
        orderRepository.save(order);

    }

    // Employee
    public String isReturnedProduct(Integer renter_id,Integer order_id){
        Renter renter = renterRepository.findRenterById(renter_id);
        Integer totalHoursDelayed = 1;
        if(renter == null){
            throw new ApiException("renter not found");
        }
        MyOrder order = orderRepository.findOrderById(order_id);
        if(order == null){
            throw new ApiException("order not found");
        }
        if(order.getIsreturned() == true){
            throw new ApiException("its already returned");
        }
        if(renter.equals(order.getRenter())){
            if(order.getOrderBlockState() == true){
                LocalDateTime dateNow = LocalDateTime.now();
                Duration duration = Duration.between(order.getEndDate(),dateNow);
                totalHoursDelayed =(int) duration.toHours();
                Double finalPrice =(double) order.getQuantity() * order.getProductPrice() * totalHoursDelayed;
                order.setIsreturned(true);
                order.setOrderBlockState(false);
                if(order.getProduct().getProductStatus().equals("Unavailable")){
                    order.getProduct().setProductStatus("Unavailable");
                }else {
                    order.getProduct().setProductStatus("Ready");
                }
                order.getProduct().setQuantity(order.getProduct().getQuantity() + order.getQuantity());
                order.setProduct(null);
                orderRepository.save(order);
                return "you have delayed :"+ totalHoursDelayed+" your extra price is now "+finalPrice;
            } else if (order.getOrderIsActive() == true) {
                order.setIsreturned(true);
                order.setOrderIsActive(false);
                order.getProduct().setProductStatus("Ready");
                order.getProduct().setQuantity(order.getProduct().getQuantity() + order.getQuantity());
                order.setProduct(null);
                orderRepository.save(order);
                return "Done ";
            }
        }else{
            throw new ApiException("order not yours");
        }
        return "error";
    }



    public List<MyOrder> orderList(Renter renter){
        return orderRepository.getMyOrdersByRenter(renter);
    }

}
