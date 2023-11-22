package cz.cvut.fit.si1.server.service;

import static cz.cvut.fit.si1.server.Data.*;
import cz.cvut.fit.si1.server.ServerApplication;
import cz.cvut.fit.si1.server.business.service.KeyService;
import cz.cvut.fit.si1.server.business.service.StudentService;
import cz.cvut.fit.si1.server.business.service.WashingMachineService;
import cz.cvut.fit.si1.server.business.dto.KeyDTO;
import cz.cvut.fit.si1.server.data.entity.Key;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.KeyRepository;
import cz.cvut.fit.si1.server.util.ErrorMessage;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ServerApplication.class)
public class KeyServiceTest {

    @Autowired
    KeyService keyService;

    @MockBean
    KeyRepository keyRepository;

    @MockBean
    StudentService studentService;

    @MockBean
    WashingMachineService washingMachineService;

    @Test
    void create() throws Exception {
        ReflectionTestUtils.setField(key,"id",1);
        ReflectionTestUtils.setField(student,"id",1);
        ReflectionTestUtils.setField(washingMachine,"id",1);

        BDDMockito.given(keyRepository.save(any(Key.class))).willReturn(key);
        BDDMockito.given(studentService.findByID(key.getStudent().getId())).willReturn(Optional.of(student));
        BDDMockito.given(washingMachineService.findByID(key.getWashingMachine().getId())).willReturn(Optional.of(washingMachine));

        Assertions.assertEquals(keyDTO.getStudent_id(), keyService.create(keyCreateDTO).getStudent_id());
        Assertions.assertEquals(keyDTO.getReturnStatus(), (keyService.create(keyCreateDTO).getReturnStatus()));
        Assertions.assertEquals(keyDTO.getWashingMachine_id(), (keyService.create(keyCreateDTO).getWashingMachine_id()));
    }

    @Test
    void update() throws Exception {

        ReflectionTestUtils.setField(key, "id",1);
        ReflectionTestUtils.setField(student,"id",1);
        ReflectionTestUtils.setField(washingMachine,"id",1);

        BDDMockito.given(keyRepository.findById(1)).willReturn(Optional.of(key));
        BDDMockito.given(keyRepository.findById(2)).willReturn(Optional.empty());
        BDDMockito.given(studentService.findByID(1)).willReturn(Optional.of(student));
        BDDMockito.given(washingMachineService.findByID(key.getWashingMachine().getId())).willReturn(Optional.of(washingMachine));

        boolean checked = false;
        try {
            keyService.update(2,keyCreateDTO);
        } catch (Exception e) {
            Assertions.assertEquals(ErrorMessage.KEY_NOT_FOUND.getMessage(), e.getMessage());
            checked = true;
        }
        if(!checked){
            throw new Exception("Failed in update");
        }

        Assertions.assertTrue(keyDTO.getStudent_id().equals(keyService.update(1, keyCreateDTO).getStudent_id()));
        Assertions.assertTrue(keyDTO.getReturnStatus().equals(keyService.update(1, keyCreateDTO).getReturnStatus()));
        Assertions.assertTrue(keyDTO.getWashingMachine_id().equals(keyService.update(1, keyCreateDTO).getWashingMachine_id()));

    }

    @Test
    void findAll() throws Exception {
        ReflectionTestUtils.setField(key1 , "id",2);
        ReflectionTestUtils.setField(student1,"id",2);
        ReflectionTestUtils.setField(washingMachine,"id",1);

        ReflectionTestUtils.setField(key2 , "id",3);
        ReflectionTestUtils.setField(student2,"id",3);

        BDDMockito.given(keyRepository.findAll()).willReturn(List.of(key1,key2));

        Assertions.assertEquals(List.of(keyDTO1,keyDTO2),keyService.findAll());
    }

    @Test
    void findByID() {
        ReflectionTestUtils.setField(key1 , "id",2);
        ReflectionTestUtils.setField(student1,"id",2);
        ReflectionTestUtils.setField(washingMachine,"id",1);

        BDDMockito.given(keyRepository.findById(key1.getId())).willReturn(Optional.of(key1));

        Assertions.assertEquals(Optional.of(key1),keyService.findById(key1.getId()));
        Mockito.verify(keyRepository, atLeastOnce()).findById(key1.getId());
    }

    @Test
    void findByIDAsDTO() throws Exception {
        ReflectionTestUtils.setField(key1 , "id",2);
        ReflectionTestUtils.setField(student1,"id",2);
        ReflectionTestUtils.setField(washingMachine,"id",1);

        BDDMockito.given(keyRepository.findById(key1.getId())).willReturn(Optional.of(key1));

        Assertions.assertEquals(Optional.of(keyDTO1),keyService.findByIdAsDTO(key1.getId()));
        Mockito.verify(keyRepository, atLeastOnce()).findById(key1.getId());
    }

    @Test
    void deleteById() throws Exception {
        ReflectionTestUtils.setField(key , "id",1);
        ReflectionTestUtils.setField(student,"id",1);
        ReflectionTestUtils.setField(washingMachine,"id",1);

        BDDMockito.given(keyRepository.findById(key.getId())).willReturn(Optional.of(key));
        BDDMockito.given(keyRepository.findById(4)).willReturn(Optional.empty());


        try {
            keyRepository.deleteById(4);
        } catch (Exception e) {
            Assertions.assertEquals("Key not found", e.getMessage());
        }

        keyService.deleteById(1);
        Mockito.verify(keyRepository, atLeastOnce()).deleteById(1);

    }

    @Test
    void returnKey() throws Exception{
        ReflectionTestUtils.setField(key , "id",1);
        ReflectionTestUtils.setField(student,"id",1);
        ReflectionTestUtils.setField(washingMachine,"id",1);

        BDDMockito.given(keyRepository.findById(key.getId())).willReturn(Optional.of(key));

        KeyDTO result = keyService.returnKey(1);

        Assertions.assertEquals(result.getStudent_id(), null);
        Assertions.assertTrue(result.getReturnStatus());
    }
}

