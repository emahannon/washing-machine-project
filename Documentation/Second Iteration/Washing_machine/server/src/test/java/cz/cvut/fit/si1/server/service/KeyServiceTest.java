package cz.cvut.fit.si1.server.service;

import cz.cvut.fit.si1.server.ServerApplication;
import cz.cvut.fit.si1.server.business.dto.KeyDTO;
import cz.cvut.fit.si1.server.business.service.serviceimpl.KeyService;
import cz.cvut.fit.si1.server.business.service.serviceimpl.StudentService;
import cz.cvut.fit.si1.server.business.service.serviceimpl.WashingMachineService;
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

import static cz.cvut.fit.si1.server.Data.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeastOnce;

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

    /**
     * Tests the create functionality.
     *
     * @throws Exception Thrown if status or washingMachine_id are null in the KeyCreateDTO object. Also, if the Student or the WashingMachine, which ids are in the KeyCreateDto object, are not found.
     */
    @Test
    void create() throws Exception {
        ReflectionTestUtils.setField(key, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyRepository.save(any(Key.class))).willReturn(key);
        BDDMockito.given(studentService.findById(key.getStudent().getId())).willReturn(Optional.of(student));
        BDDMockito.given(washingMachineService.findById(key.getWashingMachine().getId())).willReturn(Optional.of(washingMachine));

        Assertions.assertEquals(keyDTO.getStudent_id(), keyService.create(keyCreateDTO).getStudent_id());
        Assertions.assertEquals(keyDTO.getReturnStatus(), (keyService.create(keyCreateDTO).getReturnStatus()));
        Assertions.assertEquals(keyDTO.getWashingMachine_id(), (keyService.create(keyCreateDTO).getWashingMachine_id()));
    }

    /**
     * Tests the update functionality.
     *
     * @throws Exception Thrown if the Student or the WashingMachine, which ids are in the KeyCreateDto object, or the Key, which id is a parameters, are not found.
     */
    @Test
    void update() throws Exception {

        ReflectionTestUtils.setField(key, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyRepository.findById(1)).willReturn(Optional.of(key));
        BDDMockito.given(keyRepository.findById(2)).willReturn(Optional.empty());
        BDDMockito.given(studentService.findById(1)).willReturn(Optional.of(student));
        BDDMockito.given(washingMachineService.findById(key.getWashingMachine().getId())).willReturn(Optional.of(washingMachine));

        boolean checked = false;
        try {
            keyService.update(2, keyCreateDTO);
        } catch (Exception e) {
            Assertions.assertEquals(ErrorMessage.KEY_NOT_FOUND.getMessage(), e.getMessage());
            checked = true;
        }
        if (!checked) {
            throw new Exception("Failed in update");
        }

        Assertions.assertTrue(keyDTO.getStudent_id().equals(keyService.update(1, keyCreateDTO).getStudent_id()));
        Assertions.assertTrue(keyDTO.getReturnStatus().equals(keyService.update(1, keyCreateDTO).getReturnStatus()));
        Assertions.assertTrue(keyDTO.getWashingMachine_id().equals(keyService.update(1, keyCreateDTO).getWashingMachine_id()));

    }

    /**
     * Tests the findAll functionality.
     *
     * @throws Exception Thrown if key not found.
     */
    @Test
    void findAll() throws Exception {
        ReflectionTestUtils.setField(key1, "id", 2);
        ReflectionTestUtils.setField(student1, "id", 2);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        ReflectionTestUtils.setField(key2, "id", 3);
        ReflectionTestUtils.setField(student2, "id", 3);

        BDDMockito.given(keyRepository.findAll()).willReturn(List.of(key1, key2));

        Assertions.assertEquals(List.of(keyDTO1, keyDTO2), keyService.findAll());
    }

    /**
     * Tests the find by id functionality.
     */
    @Test
    void findByID() {
        ReflectionTestUtils.setField(key1, "id", 2);
        ReflectionTestUtils.setField(student1, "id", 2);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyRepository.findById(key1.getId())).willReturn(Optional.of(key1));

        Assertions.assertEquals(Optional.of(key1), keyService.findById(key1.getId()));
        Mockito.verify(keyRepository, atLeastOnce()).findById(key1.getId());
    }

    /**
     * Tests the find by id as dto functionality.
     *
     * @throws Exception Thrown if the Key, which id is the parameter, is not found.
     */
    @Test
    void findByIDAsDTO() throws Exception {
        ReflectionTestUtils.setField(key1, "id", 2);
        ReflectionTestUtils.setField(student1, "id", 2);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyRepository.findById(key1.getId())).willReturn(Optional.of(key1));

        Assertions.assertEquals(Optional.of(keyDTO1), keyService.findByIdAsDto(key1.getId()));
        Mockito.verify(keyRepository, atLeastOnce()).findById(key1.getId());
    }

    /**
     * Tests the delete by id functionality.
     *
     * @throws Exception Thrown if the Key, which id is the parameter, is not found.
     */
    @Test
    void deleteById() throws Exception {
        ReflectionTestUtils.setField(key, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

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

    /**
     * Tests the return key functionality.
     *
     * @throws Exception Thrown if the Key, which id is a parameter, is not found.
     */
    @Test
    void returnKey() throws Exception {
        ReflectionTestUtils.setField(key, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyRepository.findById(key.getId())).willReturn(Optional.of(key));

        KeyDTO result = keyService.returnKey(1);

        Assertions.assertEquals(result.getStudent_id(), null);
        Assertions.assertTrue(result.getReturnStatus());
    }
}

