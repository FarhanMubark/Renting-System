package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.DTOs.EmployeeDTO;
import com.example.rentingsystem.Model.Employee;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity getAllEmployees (){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
    }

    @PostMapping("/assing-{warehouseId}")
    public ResponseEntity assignEmployee(@AuthenticationPrincipal User user, @PathVariable Integer warehouseId){
        employeeService.assignEmployeeToWarehouse(user.getEmployee().getId(),warehouseId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee  assigned"));
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User user ,@RequestBody Employee employee){
       employeeService.update(user.getEmployee().getId(),employee);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee  updated"));
    }

}
