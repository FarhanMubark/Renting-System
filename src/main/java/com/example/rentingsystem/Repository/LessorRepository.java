package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface LessorRepository extends JpaRepository<Lessor,Integer> {

    Lessor findLessorById(Integer leesorId);
    Lessor findLessorByName(String name);

    Lessor findLessorByUser(User user);

}
