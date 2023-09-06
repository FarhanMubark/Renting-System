package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Support;
import com.example.rentingsystem.Repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportSerivce {
    private final SupportRepository supportRepository;

    public List<Support> getSupports(){
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
    }

}
