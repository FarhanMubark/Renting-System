package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Employee;
import com.example.rentingsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private  final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee,Integer employeeId){
        Employee employee1 = employeeRepository.findEmployeeById(employeeId);
        if(employee1 == null){
            throw new ApiException("employee not found");
        }
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employee1.setAge(employee.getAge());
        employee1.setEmployeeName(employee.getEmployeeName());
        employeeRepository.save(employee1);
    }

    public void removeEmployee(Integer employeeId){
        Employee employee1 = employeeRepository.findEmployeeById(employeeId);
        if(employee1 == null){
            throw new ApiException("employee not found");
        }
        employeeRepository.delete(employee1);
    }


}
