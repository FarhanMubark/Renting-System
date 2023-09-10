package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Repository.LessorRepository;
import com.example.rentingsystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final LessorRepository lessorRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Integer lessor_id, String typeOfDate,Integer duration ,Product product) {
        Lessor lessor = lessorRepository.findLessorById(lessor_id);

        if(lessor.getSubscription() == null) {
            throw new ApiException("you do not have a subscription");
        }
        if(product.getQuantity() <= 0){
            throw new ApiException("you cannot buy with Zero quantity or less");
        }
        LocalDateTime dateProduct = LocalDateTime.now();
        if(typeOfDate.equals("day") ){
            dateProduct = dateProduct.plusDays(duration);
        }else if(typeOfDate.equals("hour") ){
            dateProduct = dateProduct.plusHours(duration);
        }else{
            throw new ApiException("please enter day or hour ");
        }
        product.setProductStatus("Ready");
        product.setEndDate(dateProduct);
        product.setLessor(lessor);
        productRepository.save(product);
    }

    public void updateProduct(Integer lessor_id,Product product,Integer productId,String typeOfDate,Integer duration){
        Product product1 = productRepository.findProductById(productId);
        Lessor lessor = lessorRepository.findLessorById(lessor_id);
        if (lessor != null) {
            throw new ApiException("Wrong lessor id");
        }
        if(product1 == null){
            throw new ApiException("Product not found");
        }
        if(product.getQuantity() <= 0){
            throw new ApiException("you cannot buy with Zero quantity or less");
        }
        if(product1.getLessor().equals(lessor)) {

            LocalDateTime dateProduct = LocalDateTime.now();
            if (typeOfDate.equals("day")) {
                dateProduct = dateProduct.plusDays(duration);
            } else if (typeOfDate.equals("hour")) {
                dateProduct = dateProduct.plusHours(duration);
            } else {
                throw new ApiException("please enter day or hour ");
            }
            product1.setProductName(product.getProductName());
            product1.setProductStatus(product.getProductStatus());
            product1.setProductPrice(product.getProductPrice());
            product1.setProductCategory(product.getProductCategory());
            product.setEndDate(dateProduct);
            product1.setProductDescription(product.getProductDescription());
            productRepository.save(product1);
        }else{
            throw new ApiException("this is not your product to update with");
        }
    }

    public void removeProduct(User user,Integer productId){
        Product product1 = productRepository.findProductById(productId);
        Lessor lessor = lessorRepository.findLessorByUser(user);

        if(product1 == null){
            throw new ApiException("Product not found");
        }
        if(user.getRole().equals("ADMIN")) {
            product1.setLessor(null);
            productRepository.delete(product1);
       } else if (user.getRole().equals("LESSOR")) {

            if(product1.getLessor().equals(lessor)) {
                product1.setLessor(null);
                productRepository.delete(product1);
            }
        } else if (user.getRole().equals("RENTER")) {
            throw new ApiException("Unauthorized");
        }

    }
    //increase the number of end Date duration for the LESSOR
    public void expandDurationForProduct(Integer lessor_id, Integer product_id,String typeOfDate,Integer duration){
        Product product = productRepository.findProductById(product_id);
        Lessor lessor = lessorRepository.findLessorById(lessor_id);
        if(product == null){
            throw new ApiException("Product not found");
        }
        if(product.getLessor() != lessor){
            throw new ApiException("this is not your product to be expanded");
        }
        if(duration <= 0){
            throw new ApiException("you must put positive duration");
        }
        if(typeOfDate.equals("day") ){
            product.setEndDate(product.getEndDate().plusDays(duration));
        }else if(typeOfDate.equals("hour") ){
            product.setEndDate(product.getEndDate().plusHours(duration));
        }else{
            throw new ApiException("please enter day or hour ");
        }
        productRepository.save(product);
    }

    public List<Product> getProductsByLessor(Lessor lessor){
        return productRepository.findProductsByLessor(lessor);
    }

    public List<Product> getProductsByAvaliable(){
        return productRepository.findProductsByProductStatus();
    }

    public List<Product> getProductsByAvaliableAndPrice(){
        return productRepository.getProductsByProductPriceAndProductStatus();
    }

    public List<Product> getProductsByAvaliableAndPriceDESC(){
        return productRepository.findProductsByProductPriceAndProductStatus();
    }

    public List<Product> getProductsByName(String name){
        return productRepository.findProductsByProductName(name);
    }

}
