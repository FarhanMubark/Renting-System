package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.Service.LessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/add")
    public ResponseEntity addLessors(@RequestBody @Valid LessorDTO lessorDTO){
        lessorService.addLessor(lessorDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor Added"));
    }

    @PutMapping("/put/{id}")
    public ResponseEntity updateLessors(@RequestBody LessorDTO lessorDTO){
        lessorService.updateLessor(lessorDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteLessors(@PathVariable Integer id){
        lessorService.deleteLessor(id);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor Deleted"));
    }

    @PutMapping("{support_id}/assign/{lessor_id}")
    public ResponseEntity assignSupportToLessor(@PathVariable Integer support_id, @PathVariable Integer lessor_id){
        lessorService.assignSupportToLessor(support_id, lessor_id);
        return ResponseEntity.status(200).body(new ApiResponse("Assign Done"));
    }
}
