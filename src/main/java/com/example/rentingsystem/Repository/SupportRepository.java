package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<Support,Integer> {

    Support findSupportById(Integer supportId);

}
