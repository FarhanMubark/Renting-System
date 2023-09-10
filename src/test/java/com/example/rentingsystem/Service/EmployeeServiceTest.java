package com.example.rentingsystem.Service;

import com.example.rentingsystem.Model.Employee;
import com.example.rentingsystem.Repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    Employee employee;

    Employee employee1;

    List<Employee> employees;

    @BeforeEach
    void setup(){
        employee =new Employee(null,"abdullah","33","1990-10-07","0501020303",null,null);
        employee1 =new Employee(null,"moahmmed","23","2005-10-07","050809070",null,null);
        employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee);
    }

    // Four
    @Test
    public void getAllEmployees(){
        when(employeeRepository.findAll()).thenReturn(employees);
        List employeeList = employeeService.getEmployees();
        Assertions.assertEquals(employeeList,employees);
        verify(employeeRepository,times(1)).findAll();
    }
}
