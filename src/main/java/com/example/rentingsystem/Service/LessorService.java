package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.*;
import com.example.rentingsystem.Repository.LessorRepository;
import com.example.rentingsystem.Repository.SubscriptionRepository;
import com.example.rentingsystem.Repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LessorService {

    private final LessorRepository lessorRepository;
    private final WarehouseRepository warehouseRepository;
    private final SubscriptionRepository subscriptionRepository;


    public List<Lessor> getLessors(){
        return lessorRepository.findAll();
    }


    public void assignLessorToWarehouse(Integer lessorId ,Integer warehouseId ){
        Warehouse warehouse1 = warehouseRepository.findWarehouseById(warehouseId);
        Lessor lessor1 = lessorRepository.findLessorById(lessorId);
        if (warehouse1 == null || lessor1 == null){
            throw new ApiException("Can not assign");
        }
        lessor1.setWarehouse(warehouse1);
        lessorRepository.save(lessor1);
    }

    public void update(Integer lessorId,Lessor lessor){
        Lessor lessor1 = lessorRepository.findLessorById(lessorId);
       if(lessor1 == null){
           throw new ApiException("Can not found");
       }
       lessor1.setPhoneNumber(lessor.getPhoneNumber());
       lessor1.setName(lessor.getName());
       lessorRepository.save(lessor1);
    }
    public void lessorToSubscrive(Integer lessorId,Integer subscriptionNumber){
        Lessor lessor = lessorRepository.findLessorById(lessorId);
        if(lessor == null){
            throw new ApiException("Lessor not found");
        }
        LocalDate date = LocalDate.now();
        if (subscriptionNumber == 1){
            Subscription subscription = new Subscription(null,400,"4*4",lessor);
            LocalDate endDate = date.plusMonths(1);
            subscription.setEndDate(endDate);
            subscriptionRepository.save(subscription);
        }
        else if(subscriptionNumber == 2){
            Subscription subscription = new Subscription(null,2700,"4*4",lessor);
            LocalDate endDate = date.plusMonths(6);
            subscription.setEndDate(endDate);
            subscriptionRepository.save(subscription);
        }
        else if(subscriptionNumber == 3){
            Subscription subscription = new Subscription(null,1000,"8*8",lessor);
            LocalDate endDate = date.plusMonths(1);
            subscription.setEndDate(endDate);
            subscriptionRepository.save(subscription);
        }
        else if(subscriptionNumber == 4){
            Subscription subscription = new Subscription(null,5500,"8*8",lessor);
            LocalDate endDate = date.plusMonths(6);
            subscription.setEndDate(endDate);
            subscriptionRepository.save(subscription);
        }

    }

    public Lessor getLessorByName(String name){
        Lessor lessor = lessorRepository.findLessorByName(name);
        if(lessor == null){
            throw new ApiException("Not found");
        }
        return lessor;
    }

    public Subscription getMySubscriptions(Integer lessorId){
        Lessor lessor = lessorRepository.findLessorById(lessorId);
        if(lessor == null){
            throw new ApiException("Not found");
        }
        Subscription subscription = subscriptionRepository.findSubscriptionById(lessor.getSubscription().getId());
        if(lessor == null){
            throw new ApiException("You dont have a subscription");
        }
        return subscription;
    }
}
