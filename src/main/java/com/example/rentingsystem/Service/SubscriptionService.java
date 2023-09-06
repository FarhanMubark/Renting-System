package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Subscription;
import com.example.rentingsystem.Repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public List<Subscription> getSubscriptions(){
      return subscriptionRepository.findAll();
    }

    public void addSubscription(Subscription subscription){
        subscriptionRepository.save(subscription);
    }

    public void updateSubscription(Subscription subscription,  Integer subscriptionId){
        Subscription subscription1 = subscriptionRepository.findSubscriptionById(subscriptionId);
        if(subscription1 == null){
            throw new ApiException("Could not find subscription");
        }
        subscription1.setPrice(subscription.getPrice());
        subscription1.setSizeOfWharehose(subscription.getSizeOfWharehose());
        subscriptionRepository.save(subscription1);
    }

    public void removeSubscription(Integer subscriptionId){
        Subscription subscription1 = subscriptionRepository.findSubscriptionById(subscriptionId);
        if(subscription1 == null){
            throw new ApiException("Could not find subscription");
        }
        subscriptionRepository.delete(subscription1);
    }


}
