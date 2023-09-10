package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    Employee employee;

    @BeforeEach
    void setup(){
        employee = new Employee(null,"abdulaziz","33","1990-10-7","0590902012",null,null);
    }

    // Two
    @Test
    void findEmployeesById(){
        employeeRepository.save(employee);
        Employee employee1 = employeeRepository.findEmployeeById(employee.getId());
        Assertions.assertThat(employee1.getId()).isEqualTo(employee.getId());
    }

    // Three
    @Test
    void findEmployeesByName(){
        employeeRepository.save(employee);
        Employee employee1 = employeeRepository.findEmployeeByEmployeeName(employee.getEmployeeName());
        Assertions.assertThat(employee1.getEmployeeName()).isEqualTo(employee.getEmployeeName());
    }
}
