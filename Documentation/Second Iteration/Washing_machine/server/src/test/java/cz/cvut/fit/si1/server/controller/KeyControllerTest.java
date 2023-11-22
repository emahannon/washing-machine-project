package cz.cvut.fit.si1.server.controller;

import cz.cvut.fit.si1.server.business.dto.KeyCreateDTO;
import cz.cvut.fit.si1.server.business.dto.KeyDTO;
import cz.cvut.fit.si1.server.business.service.serviceimpl.KeyService;
import cz.cvut.fit.si1.server.business.service.serviceimpl.StudentService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static cz.cvut.fit.si1.server.Data.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class KeyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeyService keyService;

    @MockBean
    private StudentService studentService;

    /**
     * Tests the create method from the KeyController class.
     *
     * @throws Exception hrown if status or washingMachine_id are null in the KeyCreateDTO object. Also, if the Student or the WashingMachine, which ids are in the KeyCreateDto object, are not found.
     */
    @Test
    void create() throws Exception {
        BDDMockito.given(keyService.create(any(KeyCreateDTO.class))).willReturn(keyDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/key")
                        .contentType("application/json")
                        .content("{\"student\":" + keyCreateDTO.getStudent_id() + ", " +
                                "\"returnStatus\":" + keyCreateDTO.getReturnStatus() + ", " +
                                "\"washingMachine\": " + keyCreateDTO.getWashingMachine_id() + "}")
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));

    }

    /**
     * Tests the readStudentKeys method from the KeyController class.
     *
     * @throws Exception Throws exception if key not found.
     */
    @Test
    void readStudentKeys() throws Exception {
        ReflectionTestUtils.setField(key, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyService.findByStudent(key.getStudent().getId())).willReturn(keyDTO);
        BDDMockito.given(keyService.findByStudent(0)).willThrow(new Exception("Student not found"));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/key/student/{student_id}", key.getStudent().getId())
                                .contentType("application/json")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id", CoreMatchers.is(keyDTO.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("student_id", CoreMatchers.is(keyDTO.getStudent_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("returnStatus", CoreMatchers.is(keyDTO.getReturnStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("washingMachine_id", CoreMatchers.is(keyDTO.getWashingMachine_id())));


        Mockito.verify(keyService, Mockito.atLeastOnce()).findByStudent(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/key/student/{id}", 0)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    /**
     * Tests the readKey function from the KeyController class.
     *
     * @throws Exception Throws exception if key not found
     */
    @Test
    void readKey() throws Exception {
        ReflectionTestUtils.setField(key, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyService.findByIdAsDto(key.getId())).willReturn(Optional.of(keyDTO));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/key/{key_id}", key.getId())
                        .contentType("application/json")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("id", CoreMatchers.is(keyDTO.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("student_id", CoreMatchers.is(keyDTO.getStudent_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("returnStatus", CoreMatchers.is(keyDTO.getReturnStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("washingMachine_id", CoreMatchers.is(keyDTO.getWashingMachine_id())));

    }

    /**
     * Tests the ability to read in a washing machine.
     *
     * @throws Exception Throws exception if key not found.
     */
    @Test
    void readWashingMachine() throws Exception {
        ReflectionTestUtils.setField(key, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyService.findByWashingMachineAndReturnStatus(key.getWashingMachine().getId())).willReturn(List.of(keyDTO));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/key/wm/{wm_id}", key.getWashingMachine().getId())
                        .contentType("application/json")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(keyDTO.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].student_id", CoreMatchers.is(keyDTO.getStudent_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].returnStatus", CoreMatchers.is(keyDTO.getReturnStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].washingMachine_id", CoreMatchers.is(keyDTO.getWashingMachine_id())));

    }

    /**
     * Tests the findAll method.
     *
     * @throws Exception Throws exception if key not found.
     */
    @Test
    void findAll() throws Exception {
        ReflectionTestUtils.setField(key1, "id", 2);
        ReflectionTestUtils.setField(student1, "id", 2);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        ReflectionTestUtils.setField(key2, "id", 3);
        ReflectionTestUtils.setField(student2, "id", 3);


        BDDMockito.given(keyService.findAll()).willReturn(List.of(keyDTO1, keyDTO2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/key/all")
                        .contentType("application/json")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(keyDTO1.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].student_id", CoreMatchers.is(keyDTO1.getStudent_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].returnStatus", CoreMatchers.is(keyDTO1.getReturnStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].washingMachine_id", CoreMatchers.is(keyDTO1.getWashingMachine_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", CoreMatchers.is(keyDTO2.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].student_id", CoreMatchers.is(keyDTO2.getStudent_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].returnStatus", CoreMatchers.is(keyDTO2.getReturnStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].washingMachine_id", CoreMatchers.is(keyDTO2.getWashingMachine_id())));

    }

    /**
     * Tests the returnKey method.
     *
     * @throws Exception Throws an exception if the key is not found.
     */
    @Test
    void returnKey() throws Exception {
        KeyDTO keyDTOmock = new KeyDTO(1, null, true, 1);

        ReflectionTestUtils.setField(key, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(keyService.returnKey(key.getId())).willReturn(keyDTOmock);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/key/remove/{key_id}", key.getId())
                        .contentType("application/json")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("student_id", CoreMatchers.nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("returnStatus", CoreMatchers.is(true)));

    }

}
