package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {

    Subscription findSubscriptionById(Integer subscriptionId);

}
