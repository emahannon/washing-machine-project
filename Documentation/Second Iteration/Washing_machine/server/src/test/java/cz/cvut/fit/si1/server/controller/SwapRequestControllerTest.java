package cz.cvut.fit.si1.server.controller;

import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.business.service.serviceimpl.SwapRequestService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static cz.cvut.fit.si1.server.Data.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class SwapRequestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SwapRequestService swapRequestService;

    /**
     * Method to test create from the SwapRequestController.
     *
     * @throws Exception Thrown if the Student, which id is in the SwapRequestCreateDto object, is not found.
     */
    @Test
    void create() throws Exception {
        BDDMockito.given(swapRequestService.createByReason(any(), any(), any())).willReturn(swapRequestDto);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/swap/{reservation_id}/{student_id}", 123, swapRequestDto.getStudent_id())
                                .contentType("text/plain")
                                .content(swapRequestDto.getReason())
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));

    }

    /**
     * Method to test readStudentSwaprequest from the SwapRequestController.
     *
     * @throws Exception Thrown if the Student, which id is a parameter for findByStudent, is not found.
     */
    @Test
    void readStudentSwapRequests() throws Exception {
        ReflectionTestUtils.setField(swapRequest, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);

        List<SwapRequestDto> response = new ArrayList<>();
        response.add(swapRequestDto);

        BDDMockito.given(swapRequestService.findByStudent(student.getId())).willReturn(response);
        BDDMockito.given(swapRequestService.findByStudent(0)).willThrow(new Exception("Student not found"));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/swap/{student_id}", swapRequestDto.getStudent_id())
                                .contentType("application/json")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(swapRequestDto.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reason", CoreMatchers.is(swapRequestDto.getReason())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].student_id", CoreMatchers.is(swapRequestDto.getStudent_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeReq", CoreMatchers.is(swapRequestDto.getTimeReq().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", CoreMatchers.is(swapRequestDto.getStatus())));

        Mockito.verify(swapRequestService, Mockito.atLeastOnce()).findByStudent(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}", 0)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }

    /**
     * Method to test approveSwapRequest from the SwapRequestController.
     *
     * @throws Exception Thrown if the SwapRequest, which id is a parameter for findById, is not found.
     */
    @Test
    void approveSwapRequest() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);
        ReflectionTestUtils.setField(swapRequest, "id", 1);

        Answer<Void> answer1 = new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                swapRequest.setStatus(true);
                return null;
            }
        };

        BDDMockito.doAnswer(answer1).when(swapRequestService).approveSwapRequest(swapRequestDto.getId());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/swap/approve/{reservation_id}/{student_id}", reservationDto.getId(), swapRequestDto.getStudent_id())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method to test rejectSwapRequest from SwapRequestController.
     *
     * @throws Exception Thrown if the SwapRequest, which id is a parameter for findById, is not found.
     */
    @Test
    void rejectSwapRequest() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);
        ReflectionTestUtils.setField(swapRequest, "id", 1);

        Answer<Void> answer1 = new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                return null;
            }
        };

        BDDMockito.doAnswer(answer1).when(swapRequestService).deleteByIdAndReservationId(1, swapRequestDto.getId());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/swap/{reservation_id}/{student_id}", reservationDto.getId(), swapRequestDto.getStudent_id())
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(swapRequestService, Mockito.atLeastOnce()).deleteByIdAndReservationId(1, swapRequestDto.getId());

    }


}
