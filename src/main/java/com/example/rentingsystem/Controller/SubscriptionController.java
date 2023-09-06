package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.Subscription;
import com.example.rentingsystem.Service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/")
    public ResponseEntity getAllSubscriptions(){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.getSubscriptions());
    }

    @PostMapping("/add")
    public ResponseEntity addSubscription(@RequestBody @Valid Subscription subscription){
        subscriptionService.addSubscription(subscription);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Subscription added successfully"));
    }

    @PutMapping("/update/{subscriptionId}")
    public ResponseEntity updateSubscription(@RequestBody @Valid Subscription subscription, @PathVariable Integer subscriptionId){
        subscriptionService.updateSubscription(subscription,subscriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Subscription updated successfully"));
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity deleteSubscription(@PathVariable Integer subscriptionId){
        subscriptionService.removeSubscription(subscriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Subscription deleted successfully"));
    }
}
