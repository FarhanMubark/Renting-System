package com.example.rentingsystem.Service;

import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Repository.LessorRepository;
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
public class LessorServiceTest {

    @InjectMocks
    LessorService lessorService;

    @Mock
    LessorRepository lessorRepository;

    Lessor lessor;

    Lessor lessor1;

    List<Lessor> lessors;


    @BeforeEach
    void setup(){
        lessor = new Lessor(null,"abdulaziz","azozo180@gmail.com","active","0507030207",0.0,null,null,null,null,null);
        lessor1 = new Lessor(null,"abdullah","abdullah12@gmail.com","active","0560121238",0.0,null,null,null,null,null);
        lessors = new ArrayList<>();
        lessors.add(lessor);
        lessors.add(lessor1);
    }

    // Five
    @Test
    public void getAllLessor(){
        when(lessorRepository.findAll()).thenReturn(lessors);

        List lessorList = lessorService.getLessors();
        Assertions.assertEquals(lessorList,lessors);
        verify(lessorRepository,times(1)).findAll();
    }
}
