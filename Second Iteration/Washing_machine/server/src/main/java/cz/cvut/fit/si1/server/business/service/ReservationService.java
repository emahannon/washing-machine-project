package cz.cvut.fit.si1.server.business.service;

import cz.cvut.fit.si1.server.business.dto.ReservationCreateDto;
import cz.cvut.fit.si1.server.business.dto.ReservationDto;
import cz.cvut.fit.si1.server.business.dto.SendSwapListForDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.data.entity.Reservation;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.SwapRequest;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.ReservationRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.StudentRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.SwapRequestRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.WashingMachineRepository;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final StudentRepository studentRepository;
    private final WashingMachineRepository washingMachineRepository;
    private final SwapRequestRepository swapRequestRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, StudentRepository studentRepository, WashingMachineRepository washingMachineRepository, SwapRequestRepository swapRequestRepository) {
        this.reservationRepository = reservationRepository;
        this.studentRepository = studentRepository;
        this.washingMachineRepository = washingMachineRepository;
        this.swapRequestRepository = swapRequestRepository;
    }

    public ReservationDto create(ReservationCreateDto reservationCreateDto) throws Exception {
        if (reservationCreateDto.getCreateDate() == null || reservationCreateDto.getEndTime() == null || reservationCreateDto.getStartTime() == null) {
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());
        }

        Reservation reservation = new Reservation(
                reservationCreateDto.getCreateDate(),
                reservationCreateDto.getStartTime(),
                reservationCreateDto.getEndTime(),
                studentRepository.findById(reservationCreateDto.getStudent_id())
                        .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())),
                washingMachineRepository.findById(reservationCreateDto.getWashingMachine_id())
                        .orElseThrow(() -> new Exception(ErrorMessage.WM_NOT_FOUND.getMessage())));

        return toDTO(reservationRepository.save(reservation));
    }

    @Transactional
    public void updateSwapRequest(SwapRequestDto swapRequestDto) throws Exception {
        reservationRepository.findReservationBySwapReq_Id(swapRequestDto.getId())
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()))
                .setStudent(studentRepository.findById(swapRequestDto.getStudent_id())
                        .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())));
    }

    @Transactional
    public void deleteSwapRequest(Integer reservation_id) throws Exception {
        reservationRepository.findById(reservation_id)
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()))
                .setSwapReq(null);
    }

    @Transactional
    public void addSwapRequest(Integer reservation_id, SwapRequest swapRequest) throws Exception {
        if (swapRequest.getTimeReq() == null || swapRequest.getStudent() == null)
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());

        reservationRepository.findById(reservation_id)
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()))
                .setSwapReq(swapRequest);
    }

    public Optional<Reservation> findByID(Integer id) throws Exception {
        return Optional.ofNullable(reservationRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())));
    }

    public Optional<ReservationDto> findByIDAsDTO(Integer id) throws Exception {
        return toDTO(Optional.ofNullable(reservationRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()))));
    }

    public List<ReservationDto> findByMachine(Integer WashingMachine_ID) throws Exception {
        Optional<WashingMachine> optionalWashingMachine = washingMachineRepository.findById(WashingMachine_ID);
        if (optionalWashingMachine.isEmpty()) {
            throw new Exception(ErrorMessage.WM_NOT_FOUND.getMessage());
        }
        return reservationRepository.findByWashingMachine(optionalWashingMachine.get())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ReservationDto> findByStudent(Integer Student_ID) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(Student_ID);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }
        return reservationRepository.findByStudent(optionalStudent.get())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ReservationDto findBySwapRequest(Integer SwapRequest_ID) throws Exception {
        Optional<SwapRequest> optionalSwapRequest = swapRequestRepository.findById(SwapRequest_ID);
        if (optionalSwapRequest.isEmpty()) {
            throw new Exception(ErrorMessage.SR_NOT_FOUND.getMessage());
        }

        return toDTO(reservationRepository
                .findReservationBySwapReq_Id(optionalSwapRequest.get().getId())
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())));
    }

    private SendSwapListForDto toSSLDTO(Reservation reservation, SwapRequest swapRequest) {
        return new SendSwapListForDto(swapRequest.getId(), reservation.getId(),
                swapRequest.getStudent().getId(), reservation.getStudent().getId(),
                reservation.getWashingMachine().getId(), reservation.getStartTime(),
                reservation.getEndTime()
        );
    }

    public List<SendSwapListForDto> findAllSwapRequestToStudent(Integer Student_ID) {
        List<Reservation> reservation = reservationRepository.findByStudent_Id(Student_ID);
        List<SendSwapListForDto> sendSwapListForDtos = new java.util.ArrayList<>(Collections.emptyList());
        for (Reservation elem : reservation) {
            if (elem.getSwapReq() != null && !elem.getSwapReq().getStatus()) {
                sendSwapListForDtos.add(toSSLDTO(elem, elem.getSwapReq()));
            }
        }
        return sendSwapListForDtos;
    }

    private ReservationDto toDTO(Reservation reservation) {
        return new ReservationDto(reservation.getId(),
                reservation.getCreatedDate(), reservation.getStartTime(),
                reservation.getEndTime(), reservation.getStudent().getId(),
                reservation.getWashingMachine().getId()
        );
    }

    private Optional<ReservationDto> toDTO(Optional<Reservation> optionalReservation) {
        if (optionalReservation.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalReservation.get()));
    }

}
