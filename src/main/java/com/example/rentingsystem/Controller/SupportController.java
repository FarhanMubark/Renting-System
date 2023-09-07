package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Service.SupportSerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/support")
@RequiredArgsConstructor
public class SupportController {

    private final SupportSerivce supportSerivce;

/*    @GetMapping("/")
    public ResponseEntity getSupports(){
        return ResponseEntity.status(HttpStatus.OK).body(supportSerivce.getSupports());
    }
    @PostMapping("/")
    public ResponseEntity addSupport(@RequestBody @Valid Support support){
        supportSerivce.addSupports(support);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added"));
    }
    @DeleteMapping("/{supportId}")
    public ResponseEntity removeSupport(@PathVariable Integer supportId){
        supportSerivce.removeSupports(supportId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("deleted"));
    }
    @PutMapping("/{supportId}")
    public ResponseEntity updateSupport(@RequestBody @Valid Support support, @PathVariable Integer supportId){
        supportSerivce.updateSupport(support,supportId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("updated"));
    }*/
}

