package cz.cvut.fit.si1.server.controller;

import cz.cvut.fit.si1.server.business.service.serviceimpl.StudentService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static cz.cvut.fit.si1.server.Data.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    /**
     * Method to test the create method for the StudentController.
     *
     * @throws Exception Thrown if the FirstName or the LastName is null.
     */
    @Test
    void create() throws Exception {
        BDDMockito.given(studentService.create(studentCreateDto)).willReturn(studentDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .contentType("application/json")
                        .content("{\"currentOccupent\":" + studentDto.getCurrentOccupent() + ", " +
                                "\"peneltyPoints\":" + studentDto.getPeneltyPoints() + ", " +
                                "\"firstName\":\"" + studentDto.getFirstName() + "\"," +
                                " \"lastName\":\"" + studentDto.getLastName() + "\" }")
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));

        Mockito.verify(studentService, Mockito.atLeastOnce()).create(studentCreateDto);

    }

    /**
     * Method to test readStudent for the StudentController.
     *
     * @throws Exception Thrown if student is not found.
     */
    @Test
    void readStudent() throws Exception {
        BDDMockito.given(studentService.findStudentRoomById(1)).willReturn(Optional.of(studentRoomDto));
        BDDMockito.given(studentService.findStudentRoomById(0)).willReturn(Optional.empty());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/student/{id}", studentDto.getId())
                                .contentType("application/json")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.peneltyPoints", CoreMatchers.is(studentRoomDto.getPeneltyPoints())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(studentRoomDto.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(studentRoomDto.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomNumber", CoreMatchers.is(studentRoomDto.getRoomNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameBuilding", CoreMatchers.is(studentRoomDto.getNameBuilding())));

        Mockito.verify(studentService, Mockito.atLeastOnce()).findStudentRoomById(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}", 0)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Mockito.verify(studentService, Mockito.atLeastOnce()).findStudentRoomById(0);
    }

}
