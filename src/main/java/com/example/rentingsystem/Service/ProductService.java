package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product,Integer productId){
        Product product1 = productRepository.findProductById(productId);
        if(product1 == null){
            throw new ApiException("Product not found");
        }
        product1.setProductName(product.getProductName());
        product1.setProductStatus(product.getProductStatus());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductCategory(product.getProductCategory());
        product1.setEndDate(product.getEndDate());
        product1.setProductDescription(product.getProductDescription());
        productRepository.save(product1);
    }

    public void removeProduct(Integer productId){
        Product product1 = productRepository.findProductById(productId);
        if(product1 == null){
            throw new ApiException("Product not found");
        }
        productRepository.delete(product1);
    }


}
