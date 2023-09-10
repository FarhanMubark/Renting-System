package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.OrderSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderSerivce orderSerivce;

    @GetMapping("/")
    public ResponseEntity getOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderSerivce.getOrders());
    }


    @PutMapping("/is-return/{renter_id}/{order_id}")
    public ResponseEntity isReturnOrder(@AuthenticationPrincipal User user, @PathVariable Integer renter_id,@PathVariable Integer order_id){
        return ResponseEntity.status(200).body(new ApiResponse(orderSerivce.isReturnedProduct(renter_id,order_id)));
    }

    @GetMapping("/show-my-orders")
    public ResponseEntity getMyOrder(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(orderSerivce.orderList(user.getRenter()));
    }

}
