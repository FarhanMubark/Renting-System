package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.DTOs.EmployeeDTO;
import com.example.rentingsystem.DTOs.LessorDTO;
import com.example.rentingsystem.Model.Employee;
import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Repository.AuthRepository;
import com.example.rentingsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final AuthRepository authRepository;
    private  final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }


    public void addEmployee(EmployeeDTO employeeDTO){
        User user = authRepository.findUserById(employeeDTO.getUserId());

                if (user == null){
            throw new ApiException("Id Not found");
        }

                Employee employee = new Employee();
                employee.setEmployeeName(employeeDTO.getEmployeeName());
                employee.setBrithDay(employee.getBrithDay());
                employee.setAge(employee.getAge());
                employee.setPhoneNumber(employee.getPhoneNumber());
                employee.setUser(user);
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
