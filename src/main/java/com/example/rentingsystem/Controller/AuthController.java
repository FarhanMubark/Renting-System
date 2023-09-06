package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registers")
@RequiredArgsConstructor
public class AuthController {

private final AuthService authService;
//    @GetMapping("/register")
//    public ResponseEntity register(@RequestBody @Valid User user){
//        authService.register(user);
//        return ResponseEntity.status(HttpStatus.OK).body("Register successfully");
//    }
}
