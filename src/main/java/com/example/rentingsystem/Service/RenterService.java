package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.DTOs.RenterDTO;
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
    //private final SupportRepository supportRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;
    public List<Renter> getRenters(){
        return renterRepository.findAll();
    }




    public void updateRenter(RenterDTO renterDTO){
/*        Renter renter = renterRepository.findRenterById(renterDTO.getUser_id());

        if (renter == null){
            throw new ApiException("Id Not found");
        }
        renter.setName(renterDTO.getName());
        renter.setEmail(renterDTO.getEmail());
        renter.setPhoneNumber(renterDTO.getPhoneNumber());

        renterRepository.save(renter);*/
    }




    public void deleteRenter(Integer id){
        Renter renter = renterRepository.findRenterById(id);

        if (renter == null){
            throw new ApiException("Id Not found");
        }

        renterRepository.delete(renter);

    }



/*    public void assignSupportToRenter(Integer support_id, Integer renter_id){
        Support support = supportRepository.findSupportById(support_id);
        Renter renter = renterRepository.findRenterById(renter_id);

                if (support==null || renter == null){
            throw new ApiException("can't assign");
        }
                //renter.setSupport(support);
                renterRepository.save(renter);
    }*/

}
