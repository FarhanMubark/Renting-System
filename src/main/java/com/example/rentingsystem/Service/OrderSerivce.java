package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.*;
import com.example.rentingsystem.Repository.*;
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
    private final CommentRepository commentRepository;
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
    public String isReturnedProduct(Integer renter_id,Integer order_id, Double rate,String comment){
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
        if(rate > 5 || rate < 0){
            throw new ApiException("invalid rate please rate from 0 to 5 ");
        }
        if(renter.equals(order.getRenter())){
            // if the order is late
            if(order.getOrderBlockState() == true){
                // getting totalprice after he got delayed
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
                order.getRenter().setNumberOfWarning(order.getRenter().getNumberOfWarning()+1);

                // Rate
                Comment comment1 = new Comment(null,order.getRenterName(),rate,comment,order.getProduct().getLessor());
                order.getProduct().getLessor().setNumberOfRenters(order.getProduct().getLessor().getNumberOfRenters()+1);
                order.getProduct().getLessor().setRate((order.getProduct().getLessor().getRate()+rate)/order.getProduct().getLessor().getNumberOfRenters());
                order.setProductStatus("Returned");

                commentRepository.save(comment1);
                orderRepository.save(order);
                order.setProduct(null);
                orderRepository.save(order);
                return "you have delayed :"+ totalHoursDelayed+" your extra price is now "+finalPrice;
            } else if (order.getOrderIsActive() == true) {
                order.setIsreturned(true);
                order.setOrderIsActive(false);
                order.getProduct().setProductStatus("Ready");
                order.setProductStatus("Returned");
                order.getProduct().setQuantity(order.getProduct().getQuantity() + order.getQuantity());
                // Rate
                Comment comment1 = new Comment(null,order.getRenterName(),rate,comment,order.getProduct().getLessor());
                order.getProduct().getLessor().setNumberOfRenters(order.getProduct().getLessor().getNumberOfRenters()+1);
                order.getProduct().getLessor().setRate((order.getProduct().getLessor().getRate()+rate)/order.getProduct().getLessor().getNumberOfRenters());
                commentRepository.save(comment1);
                orderRepository.save(order);
                order.setProduct(null);
                orderRepository.save(order);
                return "Done";
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
