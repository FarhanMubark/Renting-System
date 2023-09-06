package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Service.OrderSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderSerivce orderSerivce;

    @GetMapping("/")
    public ResponseEntity getOrder(){
        return ResponseEntity.status(HttpStatus.OK).body(orderSerivce.getOrders());
    }

}
