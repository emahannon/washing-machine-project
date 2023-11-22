package cz.cvut.fit.si1.server.presentation.controller;

import cz.cvut.fit.si1.server.business.dto.StudentCreateDto;
import cz.cvut.fit.si1.server.business.dto.StudentDto;
import cz.cvut.fit.si1.server.business.dto.StudentRoomDto;
import cz.cvut.fit.si1.server.business.service.serviceimpl.StudentService;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private final StudentService studentService;

    /**
     * Constructor for the StudentController class.
     *
     * @param studentService Instance of the Student Service Class
     */
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Method to create the student DTO.
     *
     * @param studentCreateDto Instance of the StudentCreateDto Object.
     * @return ResponseEntity StudentDto - JSON with the student DTO of the newly created student.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDto> create(@RequestBody StudentCreateDto studentCreateDto) {
        StudentDto created = null;
        try {
            created = studentService.create(studentCreateDto);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.NOT_NULL_VARIABLES.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        assert created != null;
        return ResponseEntity.created(URI.create("http://localhost:8080/swap/" + created.getId())).body(created);
    }

    /**
     * Method to read the student by student ID.
     *
     * @param studentId Integer that represents the id of the Student
     * @return StudentRoomDto - belonging to the student
     */
    @GetMapping("/{studentId}")
    public StudentRoomDto readStudent(@PathVariable int studentId) {
        Optional<StudentRoomDto> optionalStudentRoomDto = Optional.empty();
        try {
            optionalStudentRoomDto = studentService.findStudentRoomById(studentId);
        } catch (Exception e) {
            if (ErrorMessage.getErrorMessageList().contains(e.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        if (optionalStudentRoomDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalStudentRoomDto.get();
    }
}
