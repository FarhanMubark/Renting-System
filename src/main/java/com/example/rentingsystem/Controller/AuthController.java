package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.DTOs.EmployeeDTO;
import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.DTOs.RenterDTO;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registers")
@RequiredArgsConstructor
public class AuthController {

private final AuthService authService;


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(authService.getUsers());
    }




    @PostMapping("/add-user")
    public ResponseEntity AddUser(@RequestBody @Valid User user){
        authService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Added"));
    }

    @PutMapping("/block-renter/{renter_id}")
    public ResponseEntity blockRenter(@PathVariable Integer renter_id){
        authService.setBlockToRenter(renter_id);
        return ResponseEntity.status(200).body("Renter Blocked");
    }

    @PutMapping("/block-lessor/{lessor_id}")
    public ResponseEntity blockLessor(@PathVariable Integer lessor_id){
        authService.setBlockToLesssor(lessor_id);
        return ResponseEntity.status(200).body("Lessor Blocked");
    }

    @PostMapping("/add-renter")
    public ResponseEntity addRenter(@RequestBody @Valid RenterDTO renterDTO){
        authService.addRenter(renterDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Renter Added"));
    }
    @PostMapping("/add-lessors")
    public ResponseEntity addLessors(@RequestBody @Valid LessorDTO lessorDTO){
        authService.addLessor(lessorDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor Added"));
    }
    @PostMapping("/add-employee")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        authService.addEmployee(employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Added"));
    }


    @DeleteMapping("/delete/{userName}")
    public ResponseEntity deleteUser(@PathVariable String userName){
        authService.deleted(userName);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }

    @GetMapping("/getinfo")
    public ResponseEntity getInfo(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(authService.getInfo(user.getUsername()));
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User user, @RequestBody @Valid User newUser) {
        authService.update(user.getUsername(), newUser);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("updated successfully"));
    }

}
