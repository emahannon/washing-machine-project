package cz.cvut.fit.si1.server.business.service.serviceimpl;

import cz.cvut.fit.si1.server.business.dto.SwapRequestCreateDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.business.service.baseservice.SwapRequestBaseService;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.SwapRequest;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.ReservationRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.StudentRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.SwapRequestRepository;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SwapRequestService implements
        SwapRequestBaseService<SwapRequestDto, SwapRequestCreateDto, SwapRequest, Integer> {
    private final SwapRequestRepository swapRequestRepository;
    private final StudentService studentService;
    private final ReservationService reservationService;

    /**
     * Constructor for the SwapRequestService class.
     *
     * @param swapRequestRepository Instance of the Swap Request Repository Interface
     * @param studentService        Instance of the Student Service Class
     * @param reservationService    Instence of the Reservation Sevices Class
     */
    @Autowired
    public SwapRequestService(SwapRequestRepository swapRequestRepository, StudentService studentService, ReservationRepository reservationRepository, StudentRepository studentRepository, ReservationService reservationService) {
        this.swapRequestRepository = swapRequestRepository;
        this.studentService = studentService;
        this.reservationService = reservationService;
    }

    /**
     * Method to create a swap request DTO.
     *
     * @param reason         Resaon for requesting swap request
     * @param reservation_id The ID of the reservation the swap request is for
     * @param student_id     The ID of the student requesting the swap request
     * @return SwapRequestDto - SwapRequestDto object resulting from the creation of the new Swap Request
     * @throws Exception Thrown if the Student, which id is in the SwapRequestCreateDto object, is not found
     */
    public SwapRequestDto createByReason(String reason, Integer reservation_id, Integer student_id) throws Exception {

        Optional<Student> optionalStudent = studentService.findById(student_id);

        if (optionalStudent.isEmpty())
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());

        SwapRequest swapRequest = swapRequestRepository.save(new SwapRequest(Instant.now(), reason, false,
                optionalStudent.get()));
        reservationService.addSwapRequest(reservation_id, swapRequest);
        return toDto(swapRequestRepository.save(swapRequest));

    }

    /**
     * Method exists for futur application growth, currently unused
     */
    @Override
    public SwapRequestDto create(SwapRequestCreateDto createDto) throws Exception {
        return null;
    }

    /**
     * Method to update a swap request DTO.
     *
     * @param id                   Integer that represents the id of the Swap Request that is going to be updated
     * @param swapRequestCreateDto swapRequestCreateDto object with the information to update the Swap Request
     * @return SwapRequestDto - SwapRequestDto object resulting from the creation of the new Swap Request
     * @throws Exception Thrown if the Student, which id is in the SwapRequestCreateDto object, or the SwapRequest, which id is a parameter, are not found
     */
    @Transactional
    public SwapRequestDto update(Integer id, SwapRequestCreateDto swapRequestCreateDto) throws Exception {
        Optional<SwapRequest> optionalSwapRequest = swapRequestRepository.findById(id);
        if (optionalSwapRequest.isEmpty()) {
            throw new Exception(ErrorMessage.SR_NOT_FOUND.getMessage());
        }

        SwapRequest swapRequest = optionalSwapRequest.get();
        Optional<Student> optionalStudent = studentService.findById(swapRequestCreateDto.getStudent_id());
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }

        swapRequest.setReason(swapRequestCreateDto.getReason());
        swapRequest.setStudent(optionalStudent.get());
        return toDto(swapRequest);
    }

    /**
     * Method to approve a given swapRequestDto.
     *
     * @param swap_req_id id of swam request that has been approved
     * @throws Exception Thrown if the SwapRequest, which id is in the SwapRequestDto, is not found
     */
    @Transactional
    public void approveSwapRequest(Integer swap_req_id) throws Exception {
        SwapRequestDto swapRequestDto = this.findByIdAsDto(swap_req_id).get();

        Optional<SwapRequest> optionalSwapRequest = swapRequestRepository.findById(swapRequestDto.getId());
        if (optionalSwapRequest.isEmpty()) {
            throw new Exception(ErrorMessage.SR_NOT_FOUND.getMessage());
        }

        SwapRequest swapRequest = optionalSwapRequest.get();
        swapRequest.setStatus(true);
        reservationService.updateSwapRequest(swapRequestDto);
    }

    /**
     * Finds and returns a list of all SwapRequestDtos.
     *
     * @return List{@literal <SwapRequestDto>} - List of all the SwapRequestDto
     */
    public List<SwapRequestDto> findAll() {
        List<SwapRequest> swapRequestList = swapRequestRepository.findAll();
        List<SwapRequestDto> list = new ArrayList<SwapRequestDto>();
        for (SwapRequest swapRequest : swapRequestList) {
            list.add(toDto(swapRequest));
        }
        return list;
    }

    /**
     * Finds and returns a List of SwapRequestDtos searching by student_id.
     *
     * @param student_id Integer that represents the id of the student that we want to fetch his SwapRequestDtos from
     * @return List{@literal <SwapRequestDto>} - List of all the SwapRequestDto belonging to the Student
     * @throws Exception Thrown if the Student, which id is a parameter, is not found
     */
    public List<SwapRequestDto> findByStudent(Integer student_id) throws Exception {
        Optional<Student> optionalStudent = studentService.findById(student_id);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }
        List<SwapRequest> swapRequestList = swapRequestRepository.findByStudent(optionalStudent.get());
        return swapRequestList.stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Finds and returns a SwapRequest searching by ID.
     *
     * @param id Integer representing the id of the SwapRequest we are looking for
     * @return SwapRequest - SwapRequest object we wanted to find
     * @throws Exception Thrown if the SwapRequest, which id is a parameter, is not found
     */
    public Optional<SwapRequest> findById(Integer id) throws Exception {
        return Optional.ofNullable(swapRequestRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.SR_NOT_FOUND.getMessage())));
    }

    /**
     * Finds and returns a SwapRequest Dto searching by ID.
     *
     * @param id Integer representing the id of the SwapRequest we are looking for
     * @return SwapRequestDto - SwapRequestDto object we wanted to find
     * @throws Exception Thrown if the SwapRequest, which id is a parameter, is not found
     */
    public Optional<SwapRequestDto> findByIdAsDto(Integer id) throws Exception {
        return this.toDto(Optional.ofNullable(swapRequestRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.SR_NOT_FOUND.getMessage()))));
    }

    @Override
    public void deleteById(Integer id) throws Exception {

    }

    /**
     * Deletes a SwapRequest searching by ID.
     *
     * @param reservation_id Integer represents the ID of the reservation the swap request if for
     * @param id             Integer representing the id of the SwapRequest we want to delete
     * @throws Exception Thrown if the SwapRequest, which id is a parameter, is not found
     */
    public void deleteByIdAndReservationId(Integer reservation_id, Integer id) throws Exception {
        reservationService.deleteSwapRequest(reservation_id);
        Optional<SwapRequest> optionalSwapRequest = swapRequestRepository.findById(id);
        if (optionalSwapRequest.isEmpty()) {
            throw new Exception(ErrorMessage.SR_NOT_FOUND.getMessage());
        }

        swapRequestRepository.deleteById(id);
    }

    /**
     * Creates and returns a new swap request DTO based on a swap request.
     *
     * @param swapRequest SwapRequest object we want to convert to DTO
     * @return SwapRequestDto - SwapRequest converted to DTO
     */
    public SwapRequestDto toDto(SwapRequest swapRequest) {
        return new SwapRequestDto(swapRequest.getId(),
                swapRequest.getReason(),
                swapRequest.getStudent().getId(),
                swapRequest.getTimeReq(),
                swapRequest.getStatus());
    }

    /**
     * Creates and returns a new swap request DTO based on a swap request. (Optional)
     *
     * @param optionalSwapRequest Optional{@literal <SwapRequest>} we want to convert to Optional{@literal <SwapRequestDto>}
     * @return Optional{@literal <SwapRequestDto>} - Optional{@literal <SwapRequest>} converted to DTO
     */
    public Optional<SwapRequestDto> toDto(Optional<SwapRequest> optionalSwapRequest) {
        if (optionalSwapRequest.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDto(optionalSwapRequest.get()));
    }
}
