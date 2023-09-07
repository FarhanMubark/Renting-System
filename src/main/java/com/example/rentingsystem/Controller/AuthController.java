package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.DTOs.EmployeeDTO;
import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.DTOs.RenterDTO;
import com.example.rentingsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(authService.getUsers());
    }

    @PostMapping("/add-renter")
    public ResponseEntity addUser(@RequestBody @Valid RenterDTO renterDTO){
        authService.addUser(renterDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Renter Added"));
    }
    @PostMapping("/add-lessors")
    public ResponseEntity addLessors(@RequestBody @Valid LessorDTO lessorDTO){
        authService.addUser2(lessorDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor Added"));
    }
    @PostMapping("/add-employee")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        authService.addEmployee(employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Added"));
    }
}
