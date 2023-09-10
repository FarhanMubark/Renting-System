package com.example.rentingsystem.Service;

import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Repository.AuthRepository;
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
public class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    AuthRepository authRepository;

    User user;
    User user1;

    List<User> users;


    @BeforeEach
    void setup(){
        user = new User(null,"abdulaziz","123","RENTER",null,null,null,null);
        user1 = new User(null,"abdullah","123","LESSOR",null,null,null,null);
        users = new ArrayList<>();
        users.add(user);
        users.add(user1);
    }
    @Test
    // One.
    public void getUsers(){
        when(authRepository.findAll()).thenReturn(users);
        List<User> listUsers = authService.getUsers();
        Assertions.assertEquals(listUsers,users);
        verify(authRepository,times(1)).findAll();
    }

    // Two
    @Test
    public void deleteUser(){
        when(authRepository.findUserByUsername(user.getUsername())).thenReturn(user);
        authService.deleted(user.getUsername());

        verify(authRepository,times(1)).findUserByUsername(user.getUsername());
        verify(authRepository,times(1)).delete(user);
    }
    // Three
    @Test
    public void getinfo(){
        when(authRepository.findUserByUsername(user.getUsername())).thenReturn(user);
        authService.getInfo(user.getUsername());
        verify(authRepository,times(1)).findUserByUsername(user.getUsername());
    }





}
