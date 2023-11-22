package cz.cvut.fit.si1.server.business.service.serviceimpl;

import cz.cvut.fit.si1.server.business.dto.ReservationCreateDto;
import cz.cvut.fit.si1.server.business.dto.ReservationDto;
import cz.cvut.fit.si1.server.business.dto.SendSwapListForDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.business.service.baseservice.ReservationBaseService;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService
        implements ReservationBaseService<ReservationDto, ReservationCreateDto, Reservation, Integer> {
    private final ReservationRepository reservationRepository;
    private final StudentRepository studentRepository;
    private final WashingMachineRepository washingMachineRepository;
    private final SwapRequestRepository swapRequestRepository;

    /**
     * Constructor for the reservation service class.
     * @param reservationRepository Instance of the ReservationRepository instance
     * @param studentRepository Instance of the StudentRepository interface
     * @param washingMachineRepository  Instance of the WashingMachineRepository interface
     * @param swapRequestRepository Instance of the SwapRequestRepository interface
     */
    @Autowired
    public ReservationService(ReservationRepository reservationRepository, StudentRepository studentRepository, WashingMachineRepository washingMachineRepository, SwapRequestRepository swapRequestRepository) {
        this.reservationRepository = reservationRepository;
        this.studentRepository = studentRepository;
        this.washingMachineRepository = washingMachineRepository;
        this.swapRequestRepository = swapRequestRepository;
    }

    /**
     * Method to create a reservation DTO.
     * @param reservationCreateDto  ReservationCreateDto object with the information to create a new Reservation
     * @return ReservationDto - ReservationDto object resulting from the creation of the new Reservation
     * @throws Exception    Thrown if the Student or the WashingMachine, which ids are in the ReservationCreateDto, are not found
     */
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

        return toDto(reservationRepository.save(reservation));
    }

    /**
     * Method for future development
     * @param id
     * @param createDto
     * @return
     * @throws Exception
     */
    @Override
    public ReservationDto update(Integer id, ReservationCreateDto createDto) throws Exception {
        return null;
    }


    /**
    * Method to find all the reservations in database.
    * @return List{@literal <ReservationDto>} - List{@literal <ReservationDto>} object that has the ReservationDtos of all the reservations in the database.
    */
    @Override
    public List<ReservationDto> findAll() {
        return reservationRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Method to called when the SwapRequest has been approved and therefore, we need to change the Student that has the Reservation
     * @param swapRequestDto    SwapRequestDto that has been approved
     * @throws Exception    Thrown if the Student or Reservation, which ids are in the SwapRequestDto, are not found
     */
    @Transactional
    public void updateSwapRequest(SwapRequestDto swapRequestDto) throws Exception {
        reservationRepository.findReservationBySwapReq_Id(swapRequestDto.getId())
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()))
                .setStudent(studentRepository.findById(swapRequestDto.getStudent_id())
                        .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())));
    }

    /**
     * Method to delete a swap request from a reservation
     * @param reservation_id    The ID of the reservation that the swap request that needs to be deleted if for.
     * @throws Exception    Thrown if the no Reservation with that ID exists in database.
     */
    @Transactional
    public void deleteSwapRequest(Integer reservation_id) throws Exception {
        reservationRepository.findById(reservation_id)
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()))
                .setSwapReq(null);
    }

    /**
     * Method to add a SwapRequest to the Reservation
     * @param reservation_id    Integer that represents the id of the Reservation
     * @param swapRequest   SwapRequest object we want to add to the Reservation
     * @throws Exception    Thrown if TimeReq or Student is null. Also, if the Reservation which id is a parameter, is not found
     */
    @Transactional
    public void addSwapRequest(Integer reservation_id, SwapRequest swapRequest) throws Exception {
        if (swapRequest.getTimeReq() == null || swapRequest.getStudent() == null)
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());

        reservationRepository.findById(reservation_id)
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()))
                .setSwapReq(swapRequest);
    }

    /**
     * Finds and returns a reservation by the ID.
     * @param id    Integer that represents the id of the Reservation
     * @return Optional{@literal <Reservation>} - Optional{@literal <Reservation>} which id is the parameter
     * @throws Exception    Thrown id the Reservation, which id is the parameter, is not found
     */
    public Optional<Reservation> findById(Integer id) throws Exception {
        return Optional.ofNullable(reservationRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())));
    }

    /**
     * Finds and returns a reservation DTO by ID.
     * @param id    Integer that represents the id of the Reservation
     * @return Optional{@literal <ReservationDto>} - Optional{@literal <ReservationDto>} which is the parameter
     * @throws Exception
     */
    public Optional<ReservationDto> findByIdAsDto(Integer id) throws Exception {
        return toDto(Optional.ofNullable(reservationRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()))));
    }

    /**
     * Method for future development
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteById(Integer id) throws Exception {

    }

    /**
     * Finds and returns a list of reservation DTOs by the washing machine ID.
     * @param WashingMachine_ID     Integer that represents the id of the WashingMachine
     * @return List{@literal <ReservationDto>} - List{@literal <ReservationDto>} object that has the ReservationDtos that belong to a certain Washing Machine
     * @throws Exception    Thrown if the WashingMachine, which id is the parameter, is not found
     */
    public List<ReservationDto> findByMachine(Integer WashingMachine_ID) throws Exception {
        Optional<WashingMachine> optionalWashingMachine = washingMachineRepository.findById(WashingMachine_ID);
        if (optionalWashingMachine.isEmpty()) {
            throw new Exception(ErrorMessage.WM_NOT_FOUND.getMessage());
        }
        return reservationRepository.findByWashingMachine(optionalWashingMachine.get())
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Finds and returns a list of reservation DTOs by the student ID.
     * @param Student_ID Integer that represents the id of the Student
     * @return List{@literal <ReservationDto>} - List{@literal <ReservationDto>} that has all the ReservationDtos that belong to a certain Student
     * @throws Exception    Thrown if the Student, which id is parameter, is not found
     */
    public List<ReservationDto> findByStudent(Integer Student_ID) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(Student_ID);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }
        return reservationRepository.findByStudent(optionalStudent.get())
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Finds and returns a reservation DTO by the swap request ID.
     * @param SwapRequest_ID    Integer that represents the id of the SwapRequest
     * @return ReservationDTO - ReservationDto that belongs to the SwapRequest
     * @throws Exception
     */
    public ReservationDto findBySwapRequest(Integer SwapRequest_ID) throws Exception {
        Optional<SwapRequest> optionalSwapRequest = swapRequestRepository.findById(SwapRequest_ID);
        if (optionalSwapRequest.isEmpty()) {
            throw new Exception(ErrorMessage.SR_NOT_FOUND.getMessage());
        }

        return toDto(reservationRepository
                .findReservationBySwapReq_Id(optionalSwapRequest.get().getId())
                .orElseThrow(() -> new Exception(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())));
    }

    /**
     * Finds and returns a list of all swap requests to a student by the student ID.
     * @param Student_ID    Integer that represents the id of the Student
     * @return List{@literal <SendSwapListForDto>} - List{@literal <SendSwapListForDto>} that has all the SendSwapListForDtos of a certain Student
     */
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

    /**
     * Creates and returns a new reservation DTO based on a reservation.
     * @param reservation   Reservation to convert to DTO
     * @return ReservationDto - ReservationDto resulting from the conversion
     */
    public ReservationDto toDto(Reservation reservation) {
        return new ReservationDto(reservation.getId(),
                reservation.getCreatedDate(), reservation.getStartTime(),
                reservation.getEndTime(), reservation.getStudent().getId(),
                reservation.getWashingMachine().getId()
        );
    }

    /**
     * Creates and returns a new reservation DTO based on a reservation.
     * @param optionalReservation   Optional{@literal <Reservation>} to convert to DTO
     * @return Optional{@literal <ReservationDto>} - Optional{@literal <ReservationDto>} resulting from the conversion
     */
    public Optional<ReservationDto> toDto(Optional<Reservation> optionalReservation) {
        if (optionalReservation.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDto(optionalReservation.get()));
    }

    /**
     * Creates and returns a new SendSwapListForDTO based on a reservation and swap request.
     * @param reservation   Reservation object for the SendSwapListForDTO object
     * @param swapRequest   SwapRequest object for the SendSwapListForDTO object
     * @return SendSwapListForDTO - SendSwapListForDTO resulting from the combination ob the Reservation and the SwapRequest
     */
    private SendSwapListForDto toSSLDTO(Reservation reservation, SwapRequest swapRequest) {
        return new SendSwapListForDto(swapRequest.getId(), reservation.getId(),
                swapRequest.getStudent().getId(), reservation.getStudent().getId(),
                reservation.getWashingMachine().getId(), reservation.getStartTime(),
                reservation.getEndTime()
        );
    }

}
