package cz.cvut.fit.si1.server.presentation.controller;

import cz.cvut.fit.si1.server.business.dto.SendSwapListForDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestCreateDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.business.service.ReservationService;
import cz.cvut.fit.si1.server.business.service.SwapRequestService;
import cz.cvut.fit.si1.server.data.entity.Reservation;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/swap")
public class SwapRequestController {
    private final SwapRequestService swapRequestService;
    private final ReservationService reservationService;

    @Autowired
    public SwapRequestController(SwapRequestService swapRequestService, ReservationService reservationService) {
        this.swapRequestService = swapRequestService;
        this.reservationService = reservationService;
    }

    @PostMapping("/{reservation_id}/{student_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody String reason, @PathVariable("reservation_id") Integer reservation_id, @PathVariable("student_id") Integer student_id) {
        SwapRequestCreateDto swapRequestCreateDto = new SwapRequestCreateDto(reason, student_id, Instant.now());
        SwapRequestDto created = null;

        try {
            reservationService.findByID(reservation_id).get();
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        try {
            created = swapRequestService.create(swapRequestCreateDto);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.SR_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        try {
            reservationService.addSwapRequest(reservation_id, swapRequestService.findByID(created.getId()).get());
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage()) || e.getMessage().equals(ErrorMessage.SR_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        assert created != null;
        return ResponseEntity.created(URI.create("http://localhost:8080/swap/" + created.getId())).body("{\"id\": " + created.getId() + "}");
    }

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

    @PostMapping("/approve/{reservation_id}/{swap_req_id}")
    @ResponseStatus(HttpStatus.OK)
    public void approveSwapRequest(@PathVariable Integer reservation_id, @PathVariable Integer swap_req_id) {

        try {
            reservationService.findByID(reservation_id).get();
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        try {
            swapRequestService.approveSwapRequest(swapRequestService.findByIDAsDTO(swap_req_id).get());
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.SR_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        try {
            reservationService.updateSwapRequest(swapRequestService.findByIDAsDTO(swap_req_id).get());
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())
                    || e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
    }

    @DeleteMapping("/{reservation_id}/{swap_req_id}")
    @ResponseStatus(HttpStatus.OK)
    public void rejectSwapRequest(@PathVariable Integer reservation_id, @PathVariable Integer swap_req_id) {
        Optional<Reservation> optionalReservation = Optional.empty();
        try {
            optionalReservation = reservationService.findByID(reservation_id);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        try {
            reservationService.deleteSwapRequest(optionalReservation.get().getId());
            swapRequestService.deleteById(swap_req_id);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())
                    || e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        try {
            reservationService.deleteSwapRequest(reservation_id);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.RESERVATION_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
    }

    @GetMapping("to/{id_student}")
    public List<SendSwapListForDto> getToStudentSwapRequests(@PathVariable Integer id_student) {
        List<SendSwapListForDto> sendSwapListForDtos = null;

        sendSwapListForDtos = reservationService.findAllSwapRequestToStudent(id_student);

        return sendSwapListForDtos;
    }

}
