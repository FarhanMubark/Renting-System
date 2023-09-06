package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Repository.AuthRepository;
import com.example.rentingsystem.Repository.LessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessorService {

    private final LessorRepository lessorRepository;
    private final AuthRepository authRepository;


    public List<Lessor> getLessors(){
        return lessorRepository.findAll();
    }

    public void addLessor(LessorDTO lessorDTO){
        User user = authRepository.findUserById(lessorDTO.getUser_id());
        if (user == null){
            throw new ApiException("Id Not found");
        }
        Lessor lessor = new Lessor();
        lessor.setName(lessorDTO.getName());
        lessor.setEmail(lessorDTO.getEmail());
        lessor.setPhoneNumber(lessorDTO.getPhoneNumber());
        lessor.setUser(user);
        lessorRepository.save(lessor);
    }

    public void updateLessor(Integer id, Lessor lessor){
        Lessor lessor1 = lessorRepository.findLessorById(id);

        if (lessor1 == null){
            throw new ApiException("Id Not found");
        }

        lessor1.setBalance(lessor.getBalance());
        lessor1.setName(lessor.getName());
        lessor1.setEmail(lessor.getEmail());
        lessor1.setPhoneNumber(lessor1.getPhoneNumber());
        lessor1.setSubscription(lessor.getSubscription());

        lessorRepository.save(lessor1);
    }

    public void deleteLessor(Integer id){
        Lessor lessor = lessorRepository.findLessorById(id);

        if (lessor == null){
            throw new ApiException("Id Not found");
        }

        lessorRepository.delete(lessor);

    }


}
