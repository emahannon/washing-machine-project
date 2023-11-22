package cz.cvut.fit.si1.server.service;


import cz.cvut.fit.si1.server.ServerApplication;
import cz.cvut.fit.si1.server.business.service.StudentService;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static cz.cvut.fit.si1.server.Data.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ServerApplication.class)
public class StudentServiceTest {
    @Autowired
    StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    @Test
    void create() throws Exception {
        ReflectionTestUtils.setField(student,"id",1);

        BDDMockito.given(studentRepository.save(any(Student.class))).willReturn(student);

        Assertions.assertTrue(studentDto.equals(studentService.create(studentCreateDto)));

    }

    @Test
    void update() throws Exception {
        BDDMockito.given(studentRepository.findById(1)).willReturn(Optional.of(student));
        ReflectionTestUtils.setField(student , "id",1);
        BDDMockito.given(studentRepository.findById(2)).willReturn(Optional.empty());

        boolean checked = false;
        try {
            studentService.update(4,studentCreateDto);
        } catch (Exception e) {
            Assertions.assertEquals("Student not found", e.getMessage());
            checked = true;
        }
        if(!checked){
            throw new Exception("Failed in update");
        }

        Assertions.assertTrue(studentDto.equals(studentService.update(1, studentCreateDto)));
    }

    @Test
    void findAll() {
        ReflectionTestUtils.setField(student1 , "id",2);
        ReflectionTestUtils.setField(student2 , "id",3);

        BDDMockito.given(studentRepository.findAll()).willReturn(List.of(student1,student2));

        Assertions.assertEquals(List.of(studentDto1,studentDto2),studentService.findAll());
    }

    @Test
    void findByID() throws Exception {
        BDDMockito.given(studentRepository.findById(studentDto.getId()))
                .willReturn(Optional.of(student));

        Assertions.assertEquals(student, studentService.findByID(studentDto.getId()).get());
        Mockito.verify(studentRepository, atLeastOnce()).findById(studentDto.getId());
    }

    @Test
    void findByIDAsDTO() {
        ReflectionTestUtils.setField(student , "id",1);

        BDDMockito.given(studentRepository.findById(studentDto.getId()))
                .willReturn(Optional.of(student));


        Assertions.assertEquals(studentDto, studentService.findByIDAsDTO(studentDto.getId()).get());
        Mockito.verify(studentRepository, atLeastOnce()).findById(studentDto.getId());
    }

    @Test
    void deleteById() throws Exception {
        BDDMockito.given(studentRepository.findById(1)).willReturn(Optional.of(student));
        BDDMockito.given(studentRepository.findById(4)).willReturn(Optional.empty());


        try {
            studentRepository.deleteById(4);
        } catch (Exception e) {
            Assertions.assertEquals("Student not found", e.getMessage());
        }

        studentService.deleteById(1);
        Mockito.verify(studentRepository, atLeastOnce()).deleteById(1);

    }


}

