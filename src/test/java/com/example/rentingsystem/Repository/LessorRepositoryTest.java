package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Lessor;
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
public class LessorRepositoryTest {

    @Autowired
    LessorRepository lessorRepository;

    Lessor lessor;

    @BeforeEach
    void setup(){
        lessor = new Lessor(null,"ail","azoz190@gmail.com","active","0568080123",0.0,null,null,null,null,null);
    }

    // Four
    @Test
    void findLessorById(){
        lessorRepository.save(lessor);
        Lessor lessor1 = lessorRepository.findLessorById(lessor.getId());
        Assertions.assertThat(lessor1.getId()).isEqualTo(lessor.getId());
    }
    // Five
    @Test
    void findLessorByName(){
        lessorRepository.save(lessor);
        Lessor lessor1 = lessorRepository.findLessorByName(lessor.getName());
        Assertions.assertThat(lessor1.getName()).isEqualTo(lessor.getName());
    }
}
