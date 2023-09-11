package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;

import com.example.rentingsystem.DTOs.EmployeeDTO;

import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.DTOs.RenterDTO;
import com.example.rentingsystem.Model.*;
import com.example.rentingsystem.Repository.AuthRepository;
import com.example.rentingsystem.Repository.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;


    public List<User> getUsers(){
        return authRepository.findAll();
    }


    public void addUser(User user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    public void setBlockToRenter(Integer renter_id){
        Renter renter = renterRepository.findRenterById(renter_id);

        if (renter == null){
            throw new ApiException("Renter Not Found");
        }
        renter.getUser().setRole("BLOCKED");
        renterRepository.save(renter);
    }
    public void setBlockToLesssor(Integer lessor_id){
        Lessor lessor = lessorRepository.findLessorById(lessor_id);

        if (lessor == null){
            throw new ApiException("Lessor Not Found");
        }

        lessor.getUser().setRole("BLOCKED");
        lessorRepository.save(lessor);
    }

    public void addRenter(RenterDTO renterDTO){
        String hash = new BCryptPasswordEncoder().encode(renterDTO.getPassword());
        User user = new User(null,renterDTO.getUsername(),hash,"RENTER",null,null,null,null);
        authRepository.save(user);
        Renter renter = new Renter(null,renterDTO.getPhoneNumber(),renterDTO.getName(),renterDTO.getEmail(),0,null,null,null);
        renter.setUser(user);
        renterRepository.save(renter);
    }
    //
    public void addLessor(LessorDTO lessorDTO){
        Date date= new Date();
        Date date2= new Date();
        String hash = new BCryptPasswordEncoder().encode(lessorDTO.getPassword());
        User user = new User(null,lessorDTO.getUsername(),hash,"LESSOR",null,null,null,null);
        authRepository.save(user);

        Lessor lessor = new Lessor(null,lessorDTO.getName(),lessorDTO.getEmail(),lessorDTO.getPhoneNumber(),0.0,0,0.0,null,null,null,null,null,null);
        lessor.setUser(user);
        lessorRepository.save(lessor);
    }

    public void addEmployee(EmployeeDTO employeeDTO){
        String hash = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        User user = new User(null,employeeDTO.getUsername(),hash,"Employee",null,null,null,null);
        authRepository.save(user);
        Employee employee = new Employee(null, employeeDTO.getEmployeeName(), employeeDTO.getAge(), employeeDTO.getBrithDay(), employeeDTO.getPhoneNumber(), user,null);
        employee.setUser(user);
        employeeRepository.save(employee);
    }


    public void deleted(String userName) {
        User user1 = authRepository.findUserByUsername(userName);
        if (user1 == null){
            throw new ApiException("User not found");
        }
        authRepository.delete(user1);
    }

    public User getInfo(String userName){
        User user = authRepository.findUserByUsername(userName);
        if (user == null){
            throw new ApiException("User not found");
        }
        return user;
    }

    public void update(String userName,User user){
        User user1 = authRepository.findUserByUsername(userName);
        String hash = new BCryptPasswordEncoder().encode(user1.getPassword());
        user1.setUsername(user.getUsername());
        user1.setPassword(hash);
        authRepository.save(user1);
    }

  }




