package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.Employee;
import com.example.rentingsystem.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/add")
    public ResponseEntity addEmployee (@RequestBody @Valid Employee employee){
        employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee added "));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity updateEmployee (@RequestBody @Valid Employee employee,@PathVariable Integer employeeId){
        employeeService.updateEmployee(employee, employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee updated "));
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity removeEmployee (@PathVariable Integer employeeId){
        employeeService.removeEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("employee deleted "));
    }


}
