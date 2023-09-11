package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProducts(){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
    @PostMapping("/add/{typeOfDate}/{duration}")
    public ResponseEntity addProduct(@AuthenticationPrincipal User user,@PathVariable String typeOfDate,@PathVariable Integer duration,@RequestBody @Valid Product product){
        productService.addProduct(user.getLessor().getId(),typeOfDate,duration,product);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added"));
    }

    @PutMapping("/update/{productId}/{typeOfDate}/{duration}")
    public ResponseEntity updateProduct(@AuthenticationPrincipal User user,@RequestBody @Valid Product product,Integer productId,@PathVariable String typeOfDate,@PathVariable Integer duration){
        productService.updateProduct(user.getLessor().getId(),product,productId,typeOfDate,duration);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("updated"));
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@AuthenticationPrincipal User user,@PathVariable Integer productId){
        productService.removeProduct(user,productId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("deleted"));
    }

    @PutMapping("/expand-duration/{productId}/{typeOfDate}/{duration}")
    public ResponseEntity expandDurationProduct(@AuthenticationPrincipal User user,@PathVariable Integer productId,@PathVariable String typeOfDate,@PathVariable Integer duration) {
        productService.expandDurationForProduct(user.getLessor().getId(),productId, typeOfDate, duration);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added"));
    }

    @GetMapping("/get-products-by-lessor")
    public ResponseEntity getProductsByLessor(@AuthenticationPrincipal User user){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByLessor(user.getLessor()));
    }

    @GetMapping("/get-products-available")
    public ResponseEntity getProductsByAvailableStatus(@AuthenticationPrincipal User user){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByAvaliable());
    }

    @GetMapping("/get-products-available-by-price")
    public ResponseEntity getProductsByAvailableStatusAndPrice(@AuthenticationPrincipal User user){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByAvaliableAndPrice());
    }

    @GetMapping("/get-products-available-by-price-desc")
    public ResponseEntity getProductsByAvailableStatusAndPriceDESC(@AuthenticationPrincipal User user){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByAvaliableAndPriceDESC());
    }

    @GetMapping("/get-products-by-name/{productName}")
    public ResponseEntity getProductsByName(@AuthenticationPrincipal User user,String productName){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByName(productName));
    }


}
