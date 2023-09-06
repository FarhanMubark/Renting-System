package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessorRepository extends JpaRepository<Lessor,Integer> {

    Lessor findLessorById(Integer leesorId);

}
