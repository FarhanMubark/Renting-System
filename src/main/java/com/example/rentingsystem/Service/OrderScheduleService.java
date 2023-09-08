package com.example.rentingsystem.Service;

import com.example.rentingsystem.Model.MyOrder;
import com.example.rentingsystem.Repository.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class OrderScheduleService {
    private OrderRepository orderRepository;

    @Scheduled(fixedDelay = 60000)
    public void checkOrderEndDate(){
        List<MyOrder> orders= orderRepository.getMyOrdersByOrderIsActive();
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        if(orders!=null) {
            for (MyOrder order : orders) {
                if (order.getIsreturned() == false) {
                    if (date.compareTo(order.getEndDate()) > 0) {
                        order.setOrderIsActive(false);
                        order.setFinalWarningDate(order.getEndDate().plusDays(2));
                        order.setOrderBlockState(true);
                    }
                }
            }
        }
    }

    @Scheduled(fixedDelay = 3600000)
    public void checkOrderBlockState() {
        List<MyOrder> orders = orderRepository.getMyOrdersByOrderBlockState();
        LocalDateTime date = LocalDateTime.now();
        for (MyOrder order : orders) {
            if (order.getIsreturned() == false) {
                if (date.compareTo(order.getEndDate()) > 0) {
                    // user got blocked from our System
                    order.getRenter().getUser().setRole("BLOCKED");
                }
            }
        }
    }
}
