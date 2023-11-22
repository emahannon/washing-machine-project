package cz.cvut.fit.si1.server.presentation.controller;

import cz.cvut.fit.si1.server.business.dto.ReservationCreateDto;
import cz.cvut.fit.si1.server.business.dto.ReservationDto;
import cz.cvut.fit.si1.server.business.service.ReservationService;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody ReservationCreateDto reservationCreateDto) {
        ReservationDto reservationDto = null;
        try {
            reservationDto = reservationService.create(reservationCreateDto);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.NOT_NULL_VARIABLES.getMessage()))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        assert reservationCreateDto != null;
        return ResponseEntity.created(URI.create("http://localhost:8080/reservation/" + reservationDto.getId())).body("{\"id\": " + reservationDto.getId() + "}");
    }

    @GetMapping(value = "/student/{id_student}")
    public List<ReservationDto> getReservationsByStudent(@PathVariable Integer id_student) {
        List<ReservationDto> reservationDtos = Collections.emptyList();
        try {
            System.out.println("ok1");
            reservationDtos = reservationService.findByStudent(id_student);
        } catch (Exception e) {
            System.out.println("ok3");
            if (e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage()))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return reservationDtos;
    }

    @GetMapping(value = "/wm/{id_wm}")
    public List<ReservationDto> getReservationsByWm(@PathVariable Integer id_wm) {
        List<ReservationDto> reservationDtos = Collections.emptyList();
        try {
            reservationDtos = reservationService.findByMachine(id_wm);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.WM_NOT_FOUND.getMessage()))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return reservationDtos;
    }

    @GetMapping(value = "/swap/{id_swap}")
    public ReservationDto getReservationsBySwapRequest(@PathVariable Integer id_swap) {
        ReservationDto reservationDto = null;
        try {
            reservationDto = reservationService.findBySwapRequest(id_swap);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.SR_NOT_FOUND.getMessage()))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        assert reservationDto != null;
        return reservationDto;
    }
}
