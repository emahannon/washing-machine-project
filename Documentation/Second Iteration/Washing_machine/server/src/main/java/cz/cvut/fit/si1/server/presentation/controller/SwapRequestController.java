package cz.cvut.fit.si1.server.presentation.controller;

import cz.cvut.fit.si1.server.business.dto.SendSwapListForDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.business.service.serviceimpl.ReservationService;
import cz.cvut.fit.si1.server.business.service.serviceimpl.SwapRequestService;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/swap")
public class SwapRequestController {
    private final SwapRequestService swapRequestService;
    private final ReservationService reservationService;

    /**
     * Constructor for the SwapRequestController class.
     *
     * @param swapRequestService Instance of the Swap Request Service Class
     * @param reservationService Instance of the Reservation Service Class
     */
    @Autowired
    public SwapRequestController(SwapRequestService swapRequestService, ReservationService reservationService) {
        this.swapRequestService = swapRequestService;
        this.reservationService = reservationService;
    }

    /**
     * Method to create the swap request.
     *
     * @param reason         String that tells the reason why the swap request is submitted
     * @param reservation_id Integer that represents the id of the Reservation being asked for a swap
     * @param student_id     Integer that represents the id of the Student that wants to make the swap
     * @return ResponseEntity{@literal <String>} - JSON with the id of the new created Swap Request
     */
    @PostMapping("/{reservation_id}/{student_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody String reason, @PathVariable("reservation_id") Integer reservation_id, @PathVariable("student_id") Integer student_id) {
        SwapRequestDto created = null;
        try {
            created = swapRequestService.createByReason(reason, reservation_id, student_id);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()) || e.getMessage().equals(ErrorMessage.SR_NOT_FOUND.getMessage()) || e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        assert created != null;
        return ResponseEntity.created(URI.create("http://localhost:8080/swap/" + created.getId())).body("{\"id\": " + created.getId() + "}");
    }

    /**
     * Finds and returns a list of swap request DTOs through search by student ID.
     *
     * @param studentId Integer that represents the id of the Student
     * @return List{@literal <SwapRequestDto>} - List of SwapRequestDto belonging to the student
     */
    @GetMapping("/{studentId}")
    public List<SwapRequestDto> readStudentSwapRequests(@PathVariable Integer studentId) {
        List<SwapRequestDto> swapRequestDtoList = null;
        try {
            swapRequestDtoList = swapRequestService.findByStudent(studentId);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        assert swapRequestDtoList != null : ErrorMessage.UNKNOWN_ERROR.getMessage();
        return swapRequestDtoList;
    }

    /**
     * Method to approve the swap request.
     *
     * @param reservation_id Integer that represents the id of the Reservation
     * @param swap_req_id    Integer that represents the id of the Swap Request we are going to approve
     */
    @PostMapping("/approve/{reservation_id}/{swap_req_id}")
    @ResponseStatus(HttpStatus.OK)
    public void approveSwapRequest(@PathVariable Integer reservation_id, @PathVariable Integer swap_req_id) {

        try {
            swapRequestService.approveSwapRequest(swap_req_id);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())
                    || e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage())
                    || e.getMessage().equals(ErrorMessage.SR_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
    }

    /**
     * Method to reject the swap request.
     *
     * @param reservation_id Integer that represents the id of the Reservation
     * @param swap_req_id    Integer that represents the id of the Swap Request we are going to reject
     */
    @DeleteMapping("/{reservation_id}/{swap_req_id}")
    @ResponseStatus(HttpStatus.OK)
    public void rejectSwapRequest(@PathVariable Integer reservation_id, @PathVariable Integer swap_req_id) {
        try {
            swapRequestService.deleteByIdAndReservationId(reservation_id, swap_req_id);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())
                    || e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
    }

    /**
     * Finds and returns a list of SendSwapListFor DTOs through search by student ID.
     *
     * @param id_student Integer that represents the id of the Student
     * @return List{@literal <SendSwapListForDto>} - List with all the SendSwapListForDTO that belong to a certain student
     */
    @GetMapping("to/{id_student}")
    public List<SendSwapListForDto> getToStudentSwapRequests(@PathVariable Integer id_student) {
        List<SendSwapListForDto> sendSwapListForDtos = null;

        sendSwapListForDtos = reservationService.findAllSwapRequestToStudent(id_student);

        return sendSwapListForDtos;
    }

}
