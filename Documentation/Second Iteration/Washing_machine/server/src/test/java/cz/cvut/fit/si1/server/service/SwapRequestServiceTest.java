package cz.cvut.fit.si1.server.service;

import cz.cvut.fit.si1.server.ServerApplication;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.business.service.serviceimpl.ReservationService;
import cz.cvut.fit.si1.server.business.service.serviceimpl.SwapRequestService;
import cz.cvut.fit.si1.server.data.entity.SwapRequest;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.StudentRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.SwapRequestRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;


@SpringBootTest(classes = ServerApplication.class)
public class SwapRequestServiceTest {
    @Autowired
    SwapRequestService swapRequestService;

    @MockBean
    SwapRequestRepository swapRequestRepository;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    ReservationService reservationService;

    /**
     * Tests the create method.
     *
     * @throws Exception Thrown if the Student, which id is in the SwapRequestCreateDto object, is not found.
     */
    @Test
    void create() throws Exception {
        ReflectionTestUtils.setField(swapRequest, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);

        BDDMockito.given(swapRequestRepository.save(any(SwapRequest.class))).willReturn(swapRequest);
        BDDMockito.given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));
        doNothing().when(reservationService).addSwapRequest(1, swapRequest);
        SwapRequestDto created = swapRequestService.createByReason(swapRequestCreateDto.getReason(),
                1, swapRequestCreateDto.getStudent_id());
        Assertions.assertEquals(swapRequestDto, created);

    }

    /**
     * Tests the update method.
     *
     * @throws Exception Thrown if the Student, which id is in the SwapRequestCreateDto object, or the SwapRequest, which id is a parameter, are not found.
     */
    @Test
    void update() throws Exception {
        ReflectionTestUtils.setField(swapRequest, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);

        BDDMockito.given(swapRequestRepository.findById(1)).willReturn(Optional.of(swapRequest));
        BDDMockito.given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));


        BDDMockito.given(swapRequestRepository.findById(4)).willReturn(Optional.empty());

        boolean checked = false;
        try {
            swapRequestService.update(4, swapRequestCreateDto);
        } catch (Exception e) {
            Assertions.assertEquals(ErrorMessage.SR_NOT_FOUND.getMessage(), e.getMessage());
            checked = true;
        }
        if (!checked) {
            throw new Exception("Failed in update");
        }

        Assertions.assertEquals(swapRequestDto, swapRequestService.update(1, swapRequestCreateDto));
    }


    /**
     * Tests the findAll method.
     */
    @Test
    void findAll() {
        ReflectionTestUtils.setField(swapRequest1, "id", 2);
        ReflectionTestUtils.setField(swapRequestDto1, "student_id", 2);
        ReflectionTestUtils.setField(student1, "id", 2);
        ReflectionTestUtils.setField(swapRequest2, "id", 3);
        ReflectionTestUtils.setField(swapRequestDto2, "student_id", 3);
        ReflectionTestUtils.setField(student2, "id", 3);

        BDDMockito.given(swapRequestRepository.findAll()).willReturn(List.of(swapRequest1, swapRequest2));
        Assertions.assertEquals(List.of(swapRequestDto1, swapRequestDto2), swapRequestService.findAll());
    }


    /**
     * Tests the findByID method.
     */
    @Test
    void findByID() {
        BDDMockito.given(swapRequestRepository.findById(swapRequestDto.getId()))
                .willReturn(Optional.of(swapRequest));

        Boolean exception = false;
        SwapRequest found = null;

        try {
            found = swapRequestService.findById(swapRequestDto.getId()).get();
        } catch (Exception e) {
            if (e.getMessage().equals("Student not found")) {
                exception = true;
            }
        }

        Assertions.assertEquals(swapRequest, found);
        Mockito.verify(swapRequestRepository, atLeastOnce()).findById(swapRequestDto.getId());
    }


    /**
     * Tests the findByIDAsDTO method.
     */
    @Test
    void findByIDAsDTO() {
        ReflectionTestUtils.setField(swapRequest, "id", 1);
        ReflectionTestUtils.setField(swapRequest.getStudent(), "id", 1);

        BDDMockito.given(swapRequestRepository.findById(swapRequestDto.getId()))
                .willReturn(Optional.of(swapRequest));

        Boolean exception = false;
        SwapRequestDto found = null;

        try {
            found = swapRequestService.findByIdAsDto(swapRequestDto.getId()).get();
        } catch (Exception e) {
            if (e.getMessage().equals("Student not found")) {
                exception = true;
            }
        }

        Assertions.assertFalse(exception);
        Assertions.assertEquals(swapRequestDto, found);
        Mockito.verify(swapRequestRepository, atLeastOnce()).findById(swapRequestDto.getId());
    }

    /**
     * Tests the deleteById method.
     *
     * @throws Exception Thrown if the SwapRequest, which id is a parameter, is not found.
     */
    @Test
    void deleteById() throws Exception {
        BDDMockito.given(swapRequestRepository.findById(1)).willReturn(Optional.of(swapRequest));
        BDDMockito.given(swapRequestRepository.findById(4)).willReturn(Optional.empty());


        try {
            swapRequestRepository.deleteById(4);
        } catch (Exception e) {
            Assertions.assertEquals("Customer doesn't exist", e.getMessage());
        }

        swapRequestService.deleteByIdAndReservationId(1, swapRequest.getId());
        Mockito.verify(swapRequestRepository, atLeastOnce()).deleteById(1);

    }


    /**
     * Tests the approveSwapRequest method.
     *
     * @throws Exception Thrown if the SwapRequest, which id is in the SwapRequestDto, is not found.
     */
    @Test
    void approveSwapRequest() throws Exception {
        ReflectionTestUtils.setField(swapRequest, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);

        BDDMockito.given(swapRequestRepository.findById(swapRequestDto.getId())).willReturn(Optional.of(swapRequest));

        swapRequestService.approveSwapRequest(swapRequestDto.getId());

        BDDMockito.given(swapRequestService.findByIdAsDto(swapRequest.getId())).willReturn(Optional.of(swapRequestDto));

        Assertions.assertTrue(swapRequest.getStatus());
        Mockito.verify(swapRequestRepository, atLeastOnce()).findById(swapRequestDto.getId());
    }
}
