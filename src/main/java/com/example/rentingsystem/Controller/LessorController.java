package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.LessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessors")
@RequiredArgsConstructor
public class LessorController {
    private final LessorService lessorService;

    @GetMapping("/get")
    public ResponseEntity getLessors(){
        return ResponseEntity.status(200).body(lessorService.getLessors());
    }

    @PostMapping("/{lessor_id}-assing-{warehouseId}")
    public ResponseEntity assign(@PathVariable Integer lessor_id, @PathVariable Integer warehouseId){
        lessorService.assignLessorToWarehouse(lessor_id,warehouseId);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor assigned"));
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User user, @RequestBody Lessor lessor){
        lessorService.update(user.getLessor().getId(),lessor);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor updated"));
    }

    @PutMapping("/subscriber-{subscriberNumber}")
    public ResponseEntity lessorSubscribe(@AuthenticationPrincipal User user , @PathVariable Integer subscriberNumber){
        lessorService.lessorToSubscrive(user.getLessor().getId(), subscriberNumber);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor subscribed"));
    }

    @GetMapping("/search-{lessorName}")
    public ResponseEntity findLessorByName(@PathVariable String lessorName ){
        return ResponseEntity.status(200).body(lessorService.getLessorByName(lessorName));
    }

    @GetMapping("/findMySubscriber")
    public ResponseEntity findMySubscriber(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(lessorService.getMySubscriptions(user.getLessor().getId()));
    }

}
