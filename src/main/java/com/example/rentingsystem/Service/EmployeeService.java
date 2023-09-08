package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.DTOs.EmployeeDTO;
import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.Model.Employee;
import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Model.Warehouse;
import com.example.rentingsystem.Repository.AuthRepository;
import com.example.rentingsystem.Repository.EmployeeRepository;
import com.example.rentingsystem.Repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final AuthRepository authRepository;
    private  final EmployeeRepository employeeRepository;
    private final WarehouseRepository warehouseRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void assignEmployeeToWarehouse(Integer employeeId ,Integer warehouseId ){
        Warehouse warehouse1 = warehouseRepository.findWarehouseById(warehouseId);
        Employee employee1 = employeeRepository.findEmployeeById(employeeId);
        if (warehouse1 == null || employee1 == null){
            throw new ApiException("Can not assign");
        }
        employee1.setWarehouse(warehouse1);
        employeeRepository.save(employee1);
    }

    public void update(Integer employeeId,Employee employee){
      Employee employee1 = employeeRepository.findEmployeeById(employeeId);
      if (employee == null){
          throw new ApiException("Can not found");
      }
      employee1.setEmployeeName(employee.getEmployeeName());
      employee1.setPhoneNumber(employee.getPhoneNumber());
      employee1.setAge(employee.getAge());
      employeeRepository.save(employee1);
    }

}
