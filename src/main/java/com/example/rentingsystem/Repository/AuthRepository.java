package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {


    User findUserById(Integer userId);
    User findUserByUsername(String username);

}
