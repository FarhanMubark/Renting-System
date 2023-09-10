package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Model.Employee;
import com.example.rentingsystem.Service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmployeeController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    Employee employee;
    List<Employee> employeeList;

    @BeforeEach
    void setup(){
        employee = new Employee(1,"abdulaziz","23","1990-06-12","0501212125",null,null);
        employeeList = Arrays.asList(employee);
    }

    // Two
    @Test
    public void getEmployees() throws Exception{
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeList);
        mockMvc.perform(get("/api/v1/employees/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value("23"));

    }

}
