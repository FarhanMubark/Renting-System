package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.DTOs.RenterDTO;

import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Model.Renter;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.RenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/renters")
@RequiredArgsConstructor
public class RenterController {
    private final RenterService renterService;

    @GetMapping("/get")
    public ResponseEntity getRenters(){
        return ResponseEntity.status(200).body(renterService.getRenters());
    }

    @PutMapping("/")
    public ResponseEntity update(@AuthenticationPrincipal User user , @RequestBody Renter renter){
        renterService.update(user.getRenter().getId(),renter);
        return ResponseEntity.status(200).body(new ApiResponse("Renter updated"));
    }

    @GetMapping("/search")
    public ResponseEntity findRenterByName(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(renterService.getRenterByName(user.getRenter().getName()));
    }


    @PostMapping("/buy-product/{product_id}/{typeOfDay}/{quantity}/{duration}")
    public ResponseEntity updateProduct(@AuthenticationPrincipal User user,@PathVariable Integer product_id,@PathVariable String typeOfDay,@PathVariable Integer quantity,@PathVariable Integer duration){
        renterService.buyProduct(user.getRenter().getId(),product_id,typeOfDay,quantity,duration);
        return ResponseEntity.status(200).body(new ApiResponse("product bought successfully"));
    }

}
