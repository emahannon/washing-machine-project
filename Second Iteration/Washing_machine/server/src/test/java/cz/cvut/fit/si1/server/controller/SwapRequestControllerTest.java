package cz.cvut.fit.si1.server.controller;

import cz.cvut.fit.si1.server.business.dto.SwapRequestCreateDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.business.service.ReservationService;
import cz.cvut.fit.si1.server.business.service.SwapRequestService;
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
import java.util.Optional;

import static cz.cvut.fit.si1.server.Data.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class SwapRequestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SwapRequestService swapRequestService;

    @MockBean
    private ReservationService reservationService;

    @Test
    void create() throws Exception {
        BDDMockito.given(swapRequestService.create(any(SwapRequestCreateDto.class))).willReturn(swapRequestDto);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/swap/{reservation_id}/{student_id}",123,swapRequestDto.getStudent_id())
                                .contentType("text/plain")
                                .content(swapRequestDto.getReason())
                                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));;

    }


    @Test
    void readStudentSwapRequests() throws Exception {
        ReflectionTestUtils.setField(swapRequest , "id",1);
        ReflectionTestUtils.setField(student , "id",1);

        List<SwapRequestDto> response = new ArrayList<>();
        response.add(swapRequestDto);

        BDDMockito.given(swapRequestService.findByStudent(student.getId())).willReturn(response);
        BDDMockito.given(swapRequestService.findByStudent(0)).willThrow(new Exception("Student not found"));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/swap/{student_id}",swapRequestDto.getStudent_id())
                                .contentType("application/json")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(swapRequestDto.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reason", CoreMatchers.is(swapRequestDto.getReason())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].student_id", CoreMatchers.is(swapRequestDto.getStudent_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeReq", CoreMatchers.is(swapRequestDto.getTimeReq().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", CoreMatchers.is(swapRequestDto.getStatus())));

        Mockito.verify(swapRequestService, Mockito.atLeastOnce()).findByStudent(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}",0)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }

    @Test
    void approveSwapRequest() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);
        ReflectionTestUtils.setField(swapRequest,"id",1);

        Answer<Void> answer1 = new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                swapRequest.setStatus(true);
                return null;
            }
        };

        Answer<Void> answer2 = new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                reservation.setStudent(swapRequest.getStudent());
                return null;
            }
        };

        BDDMockito.given(reservationService.findByID(reservationDto.getId())).willReturn(Optional.of(reservation));
        BDDMockito.given(swapRequestService.findByIDAsDTO(swapRequestDto.getId())).willReturn(Optional.of(swapRequestDto));
        BDDMockito.doAnswer(answer1).when(swapRequestService).approveSwapRequest(swapRequestDto);
        BDDMockito.doAnswer(answer2).when(reservationService).updateSwapRequest(swapRequestDto);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/swap/approve/{reservation_id}/{student_id}",reservationDto.getId(), swapRequestDto.getStudent_id())
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(swapRequestService, Mockito.atLeastOnce()).findByIDAsDTO(swapRequestDto.getId());
        Mockito.verify(swapRequestService, Mockito.atLeastOnce()).approveSwapRequest(swapRequestDto);
        Mockito.verify(reservationService, Mockito.atLeastOnce()).findByID(reservationDto.getId());
        Mockito.verify(reservationService, Mockito.atLeastOnce()).updateSwapRequest(swapRequestDto);

    }

    @Test
    void deleteSwapRequest() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);
        ReflectionTestUtils.setField(swapRequest,"id",1);

        Answer<Void> answer1 = new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                return null;
            }
        };

        Answer<Void> answer2 = new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                reservation.setSwapReq(null);
                return null;
            }
        };


        BDDMockito.given(reservationService.findByID(reservationDto.getId())).willReturn(Optional.of(reservation));
        BDDMockito.doAnswer(answer1).when(swapRequestService).deleteById(swapRequestDto.getId());
        BDDMockito.doAnswer(answer2).when(reservationService).deleteSwapRequest(reservationDto.getId());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/swap/{reservation_id}/{student_id}",reservationDto.getId(), swapRequestDto.getStudent_id())
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(swapRequestService, Mockito.atLeastOnce()).deleteById(swapRequestDto.getId());
        Mockito.verify(reservationService, Mockito.atLeastOnce()).findByID(reservationDto.getId());
        Mockito.verify(reservationService, Mockito.atLeastOnce()).deleteSwapRequest(reservationDto.getId());

    }


}
