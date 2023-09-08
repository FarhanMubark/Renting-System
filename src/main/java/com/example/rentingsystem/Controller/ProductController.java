package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity getProducts(){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(Product product){
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added"));
    }

    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(Product product,Integer productId){
        productService.updateProduct(product,productId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("updated"));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(Integer productId){
        productService.removeProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("deleted"));
    }



}
