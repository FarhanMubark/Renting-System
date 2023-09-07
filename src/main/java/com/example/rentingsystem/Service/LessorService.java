package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.Support;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Repository.AuthRepository;
import com.example.rentingsystem.Repository.LessorRepository;
import com.example.rentingsystem.Repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessorService {

    private final LessorRepository lessorRepository;
    private final SupportRepository supportRepository;
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

    public void updateLessor(LessorDTO lessorDTO){
        Lessor lessor1 = lessorRepository.findLessorById(lessorDTO.getUser_id());

        if (lessor1 == null){
            throw new ApiException("Id Not found");
        }


        lessor1.setName(lessorDTO.getName());
        lessor1.setEmail(lessorDTO.getEmail());
        lessor1.setPhoneNumber(lessorDTO.getPhoneNumber());


        lessorRepository.save(lessor1);
    }

    public void deleteLessor(Integer id){
        Lessor lessor = lessorRepository.findLessorById(id);

        if (lessor == null){
            throw new ApiException("Id Not found");
        }

        lessorRepository.delete(lessor);

    }



    public void assignSupportToLessor(Integer support_id, Integer lessor_id){
        Support support = supportRepository.findSupportById(support_id);
        Lessor lessor = lessorRepository.findLessorById(lessor_id);

                if (support==null || lessor == null){
            throw new ApiException("can't assign");
        }

                lessor.setSupport(support);
                lessorRepository.save(lessor);
    }


}
