package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Model.Renter;
import com.example.rentingsystem.Repository.ProductRepository;
import com.example.rentingsystem.Repository.RenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RenterService {
    private final RenterRepository renterRepository;
    private final ProductRepository productRepository;
    private final OrderSerivce orderSerivce;

    public List<Renter> getRenters(){
        return renterRepository.findAll();
    }


    public void update(Integer renterId , Renter renter){
        Renter renter1 = renterRepository.findRenterById(renterId);
        if(renter1 == null){
            throw new ApiException("Could not find");
        }
        renter1.setEmail(renter.getEmail());
        renter1.setPhoneNumber(renter.getPhoneNumber());
        renter1.setName(renter.getName());
        renterRepository.save(renter1);
    }


public void buyProduct( Integer renter_id , Integer productId, String typrOfDate,Integer quantity,Integer duration){
    Product product = productRepository.findProductById(productId);
    Renter renter = renterRepository.findRenterById(renter_id);
    if(renter == null){
        throw new ApiException("renter Not found");
    }

    if(duration <= 0){
        throw new ApiException("you cannot put duration has 0 duration");
    }
    if (product == null){
        throw new ApiException("Product not found");
    }
    if(product.getQuantity() < quantity){
        throw new ApiException("you cannot buy quantity has more than product quantity");
    }
    if(quantity <= 0){
        throw new ApiException("you cannot buy with Zero quantity or less");
    }

    Integer totalHours = 1;
    LocalDateTime dateOrder = LocalDateTime.now();
    if(typrOfDate.equals("day")){
        totalHours = duration*24;
        dateOrder = dateOrder.plusDays(duration);
    }else if(typrOfDate.equals("hour")){
        dateOrder = dateOrder.plusHours(duration);
        totalHours = duration;
    }

    if(product.getEndDate().compareTo(dateOrder) < 0){
        throw new ApiException("you cant buy product because the End date of this product is "+product.getEndDate());
    }
    orderSerivce.addOrder(product,renter,quantity,dateOrder,totalHours);
    }



    public Renter getRenterByName(String name){
        Renter renter = renterRepository.findRenterByName(name);

        if(renter == null){
            throw new ApiException("Could not find");
        }
        return renter;
    }

}
