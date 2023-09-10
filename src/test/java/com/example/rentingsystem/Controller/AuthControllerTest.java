package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.AuthService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AuthController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class AuthControllerTest {

    @MockBean
    AuthService authService;

    @Autowired
    MockMvc mockMvc;

    User user;

    List<User> userList;


    @BeforeEach
    void setup(){
        user = new User(1,"abdulaziz","123","RENTER",null,null,null,null);
        userList = Arrays.asList(user);
    }

    // One
    @Test
    public void getUsers()throws Exception{
        Mockito.when(authService.getUsers()).thenReturn(userList);
        mockMvc.perform(get("/api/v1/registers/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("abdulaziz"));
    }

}
