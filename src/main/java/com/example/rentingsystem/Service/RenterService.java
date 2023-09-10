package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.DTOs.RenterDTO;
import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.Renter;
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
    private final AuthService authService;
    public List<Renter> getRenters(){
        return renterRepository.findAll();
    }


    public void update(Integer renterId , Renter renter){
        Renter renter1 = renterRepository.findRenterById(renterId);
        if(renter1 == null){
            throw new ApiException("Could not find");
        }
        renter1.setEmail(renter.getEmail());
        renter1.setPhoneNumber(renter.getPhoneNumber());
        renter1.setName(renter.getName());
        renterRepository.save(renter1);
    }

    public Renter getRenterByName(String name){
        Renter renter = renterRepository.findRenterByName(name);

        if(renter == null){
            throw new ApiException("Could not find");
        }
        return renter;
    }

}
