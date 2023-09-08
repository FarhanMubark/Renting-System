package com.example.rentingsystem.Service;

import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.DTOs.RenterDTO;
import com.example.rentingsystem.Model.*;
import com.example.rentingsystem.Repository.AuthRepository;
import com.example.rentingsystem.Repository.LessorRepository;
import com.example.rentingsystem.Repository.RenterRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final RenterRepository renterRepository;
    private final LessorRepository lessorRepository;


    public List<User> getUsers(){
        return authRepository.findAll();
    }

    public void addUser(RenterDTO renterDTO){
        String hash = new BCryptPasswordEncoder().encode(renterDTO.getPassword());
        User user = new User(null,renterDTO.getUsername(),hash,"RENTER",null,null,null,null);
        authRepository.save(user);
        Renter renter = new Renter(null,renterDTO.getPhoneNumber(),renterDTO.getName(),renterDTO.getStatus(),renterDTO.getEmail(),null,null,null,null);
        renter.setUser(user);
        renterRepository.save(renter);
    }
    //
    public void addUser2(LessorDTO lessorDTO){
        Date date= new Date();
        Date date2= new Date();
        String hash = new BCryptPasswordEncoder().encode(lessorDTO.getPassword());
        User user = new User(null,lessorDTO.getUsername(),hash,"LESSOR",null,null,null,null);
        authRepository.save(user);

        Lessor lessor = new Lessor(null,lessorDTO.getName(),lessorDTO.getEmail(),lessorDTO.getStatus(),lessorDTO.getPhoneNumber(),0.0,null,null,null,null,null);
        lessor.setUser(user);
        lessorRepository.save(lessor);
    }


}
