package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.DTOs.RenterDTO;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Model.Renter;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Repository.AuthRepository;
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
    //private final SupportRepository supportRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;
    public List<Renter> getRenters(){
        return renterRepository.findAll();
    }




    public void updateRenter(RenterDTO renterDTO){
/*        Renter renter = renterRepository.findRenterById(renterDTO.getUser_id());

        if (renter == null){
            throw new ApiException("Id Not found");
        }
        renter.setName(renterDTO.getName());
        renter.setEmail(renterDTO.getEmail());
        renter.setPhoneNumber(renterDTO.getPhoneNumber());

        renterRepository.save(renter);*/
    }




    public void deleteRenter(Integer id){
        Renter renter = renterRepository.findRenterById(id);

        if (renter == null){
            throw new ApiException("Id Not found");
        }

        renterRepository.delete(renter);

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

    Integer totalHours = 0;
    LocalDateTime dateOrder = LocalDateTime.now();
    if(typrOfDate == "day" ){
        totalHours = duration*24;
        dateOrder.plusDays(duration);
    }else if(typrOfDate == "hour" ){
        dateOrder.plusHours(duration);
        totalHours = duration;
    }

    if(product.getEndDate().compareTo(dateOrder) < 0){
        throw new ApiException("you cant buy product because the End date of this product is ");
    }
    orderSerivce.addOrder(product,renter,quantity,dateOrder,totalHours);
}


/*    public void assignSupportToRenter(Integer support_id, Integer renter_id){
        Support support = supportRepository.findSupportById(support_id);
        Renter renter = renterRepository.findRenterById(renter_id);

                if (support==null || renter == null){
            throw new ApiException("can't assign");
        }
                //renter.setSupport(support);
                renterRepository.save(renter);
    }*/

}
