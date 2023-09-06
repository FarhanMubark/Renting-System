package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.DTOs.RenterDTO;
import com.example.rentingsystem.Model.Renter;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Repository.AuthRepository;
import com.example.rentingsystem.Repository.RenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RenterService {
    private final RenterRepository renterRepository;
    private final AuthRepository authRepository;
    public List<Renter> getRenters(){
        return renterRepository.findAll();
    }



    public void addRenter(RenterDTO renterDTO){
        User user = authRepository.findUserById(renterDTO.getUser_id());

        if (user == null){
            throw new ApiException("Id Not found");
        }
        Renter renter = new Renter();
        renter.setName(renterDTO.getName());
        renter.setPhoneNumber(renterDTO.getPhoneNumber());
        renter.setEmail(renterDTO.getEmail());
        renter.setUser(user);
        renterRepository.save(renter);
    }


    public void  updateRenter(Integer id, Renter renter){
        Renter renter1 = renterRepository.findRenterById(id);

        if (renter1 == null){
            throw new ApiException("Id Not found");
        }

        renter1.setName(renter.getName());
        renter1.setEmail(renter.getEmail());
        renter1.setStatus(renter.getStatus());
        renter1.setPhoneNumber(renter.getPhoneNumber());

        renterRepository.save(renter1);
    }

    public void deleteRenter(Integer id){
        Renter renter = renterRepository.findRenterById(id);

        if (renter == null){
            throw new ApiException("Id Not found");
        }

        renterRepository.delete(renter);

    }
}
