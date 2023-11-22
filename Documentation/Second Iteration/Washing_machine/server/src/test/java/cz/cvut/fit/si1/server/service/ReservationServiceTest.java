package cz.cvut.fit.si1.server.service;

import cz.cvut.fit.si1.server.ServerApplication;
import cz.cvut.fit.si1.server.business.service.serviceimpl.ReservationService;
import cz.cvut.fit.si1.server.data.entity.Reservation;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.ReservationRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.StudentRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.WashingMachineRepository;
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

@SpringBootTest(classes = ServerApplication.class)
class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;

    @MockBean
    ReservationRepository reservationRepository;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    WashingMachineRepository washingMachineRepository;

    /**
     * Tests the create functionality.
     *
     * @throws Exception Thrown if the Student or the WashingMachine, which ids are in the ReservationCreateDto, are not found.
     */
    @Test
    void create() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(reservationRepository.save(any(Reservation.class))).willReturn(reservation);
        BDDMockito.given(studentRepository.findById(any(Integer.class))).willReturn(Optional.of(student));
        BDDMockito.given(washingMachineRepository.findById(any(Integer.class))).willReturn(Optional.of(washingMachine));

        Assertions.assertEquals(reservationDto, reservationService.create(reservationCreateDto));

    }

    /**
     * Tests the find by machine functionality.
     *
     * @throws Exception Thrown if the WashingMachine, which id is the parameter, is not found.
     */
    @Test
    void findByMachine() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(washingMachineRepository.findById(any(Integer.class))).willReturn(Optional.of(washingMachine));
        BDDMockito.given(reservationRepository.findByWashingMachine(washingMachine)).willReturn(List.of(reservation));

        Assertions.assertEquals(List.of(reservationDto), reservationService.findByMachine(1));
        Mockito.verify(reservationRepository, Mockito.atLeastOnce()).findByWashingMachine(washingMachine);
    }

    /**
     * Tests the find by student functionality.
     *
     * @throws Exception Thrown if the Student, which id is parameter, is not found.
     */
    @Test
    void findByStudent() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);

        BDDMockito.given(studentRepository.findById(any(Integer.class))).willReturn(Optional.of(student));
        BDDMockito.given(reservationRepository.findByStudent(student)).willReturn(List.of(reservation));

        Assertions.assertEquals(List.of(reservationDto), reservationService.findByStudent(1));
        Mockito.verify(reservationRepository, Mockito.atLeastOnce()).findByStudent(student);
    }

    /**
     * Tests the add swap request functionality.
     *
     * @throws Exception Thrown if TimeReq or Student is null. Also, if the Reservation which id is a parameter, is not found.
     */
    @Test
    void addSwapRequest() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);
        ReflectionTestUtils.setField(swapRequest, "id", 1);

        BDDMockito.given(reservationRepository.findById(reservationDto.getId())).willReturn(Optional.of(reservation));

        reservationService.addSwapRequest(reservationDto.getId(), swapRequest);

        Assertions.assertEquals(reservation.getSwapReq(), swapRequest);
        Mockito.verify(reservationRepository, Mockito.atLeastOnce()).findById(reservationDto.getId());
    }


    /**
     * Tests the delete swap request functionality.
     *
     * @throws Exception Thrown if the Reservation, which id is the parameter, is not found.
     */
    @Test
    void deleteSwapRequest() throws Exception {
        ReflectionTestUtils.setField(reservation, "id", 1);
        ReflectionTestUtils.setField(student, "id", 1);
        ReflectionTestUtils.setField(washingMachine, "id", 1);
        ReflectionTestUtils.setField(swapRequest, "id", 1);

        BDDMockito.given(reservationRepository.findById(reservationDto.getId())).willReturn(Optional.of(reservation));

        reservationService.addSwapRequest(reservationDto.getId(), swapRequest);

        reservationService.deleteSwapRequest(reservationDto.getId());

        Assertions.assertEquals(reservation.getSwapReq(), null);
        Mockito.verify(reservationRepository, Mockito.atLeastOnce()).findById(reservationDto.getId());
    }
}