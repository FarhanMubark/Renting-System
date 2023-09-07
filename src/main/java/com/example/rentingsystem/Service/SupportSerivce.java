package com.example.rentingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportSerivce {
    //private final SupportRepository supportRepository;

/*    public List<Support> getSupports(){
        return supportRepository.findAll();
    }
    public void addSupports(Support support){
        supportRepository.save(support);
    }

    public void updateSupport(Support support,Integer supportId){
        Support support1 = supportRepository.findSupportById(supportId);
        if (support1 == null){
            throw new ApiException("Could not find support");
        }
        support1.setPhoneNumber(support.getPhoneNumber());
        support1.setSupportType(support.getSupportType());
        supportRepository.save(support1);
    }

    public void removeSupports(Integer supportId){
        Support support1 = supportRepository.findSupportById(supportId);
        if (support1 == null){
            throw new ApiException("Could not find support");
        }
        supportRepository.delete(support1);
    }*/

}
