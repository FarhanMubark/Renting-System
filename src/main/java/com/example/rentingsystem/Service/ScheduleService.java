package com.example.rentingsystem.Service;

import com.example.rentingsystem.Model.MyOrder;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Model.Subscription;
import com.example.rentingsystem.Repository.OrderRepository;
import com.example.rentingsystem.Repository.ProductRepository;
import com.example.rentingsystem.Repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final OrderRepository orderRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ProductRepository productRepository;
    //1 MINUTE
    @Scheduled(fixedDelay = 60000)
    public void checkOrderEndDate(){
        List<MyOrder> orders= orderRepository.findAllByProductStatus();
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        if(orders!=null) {
            for (MyOrder order : orders) {
                if (order.getIsreturned() == false) {
                    if (date.compareTo(order.getEndDate()) > 0) {
                        order.setOrderIsActive(false);
                        order.setFinalWarningDate(order.getEndDate().plusDays(2));
                        order.setOrderBlockState(true);
                        order.setProductStatus("Delayed");
                        System.out.println("this order id is now delayed "+order.getId());
                        orderRepository.save(order);
                    }
                }
            }
        }
    }
    // 1 HOUR
    @Scheduled(fixedDelay = 3600000)
    public void checkProductEndDate() {
        List<Product> products = productRepository.findProductsByProductStatus();
        LocalDateTime date = LocalDateTime.now();
        if(products != null) {
            for (Product product : products) {
                if (date.compareTo(product.getEndDate()) > 0) {
                        // if product end date is done
                        product.setProductStatus("Unavailable");
                        System.out.println(product.getProductName() +" product is now unavaliable");
                        productRepository.save(product);
                    }

            }
        }
    }

    // 1 HOUR
    @Scheduled(fixedDelay = 3600000)
    public void checkOrderBlockState() {
        List<MyOrder> orders = orderRepository.getMyOrdersByOrderBlockState();
        LocalDateTime date = LocalDateTime.now();
        if(orders != null) {
            for (MyOrder order : orders) {
                if (order.getIsreturned() == false) {
                    if (date.compareTo(order.getEndDate()) > 0) {
                        // user got blocked from our System
                        order.setProductStatus("Stoled");
                        order.getRenter().getUser().setRole("BLOCKED");
                        System.out.println(order.getRenter().getUser().getUsername() +" user is now blocked");
                        orderRepository.save(order);
                    }
                }
            }
        }
    }


    // 24 HOUR
    @Scheduled(fixedDelay = 86400000)
    public void checkSubscriptionsEndDate() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        LocalDate date = LocalDate.now();
        if(subscriptions != null) {
            for (Subscription subscription : subscriptions) {
                if (date.compareTo(subscription.getEndDate()) > 0) {
                    subscriptionRepository.delete(subscription);

                    System.out.println(subscription.getLessor().getUser().getUsername()+" user subscription is done");
                }
            }
        }
    }





}
