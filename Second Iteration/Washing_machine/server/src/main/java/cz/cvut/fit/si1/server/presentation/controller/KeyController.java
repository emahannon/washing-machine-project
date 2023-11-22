package cz.cvut.fit.si1.server.presentation.controller;

import cz.cvut.fit.si1.server.business.dto.KeyCreateDTO;
import cz.cvut.fit.si1.server.business.dto.KeyDTO;
import cz.cvut.fit.si1.server.business.service.KeyService;
import cz.cvut.fit.si1.server.business.service.StudentService;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/key")
public class KeyController {
    private final KeyService keyService;
    private final StudentService studentService;

    @Autowired
    public KeyController(KeyService keyService, StudentService studentService) {
        this.keyService = keyService;
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<KeyDTO> create(@RequestBody KeyCreateDTO keyCreateDTO) {
        KeyDTO created = null;

        try {
            created = keyService.create(keyCreateDTO);
        } catch (Exception e) {
            if (ErrorMessage.getErrorMessageList().contains(e.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        assert created != null;
        return ResponseEntity.created(URI.create("http://localhost:8080/key/" + created.getId())).body(created);
    }

    @GetMapping("/{keyId}")
    @ResponseStatus(HttpStatus.OK)
    public KeyDTO readKey(@PathVariable Integer keyId) {
        Optional<KeyDTO> optionalKeyDTO = Optional.empty();
        try {
            optionalKeyDTO = keyService.findByIdAsDTO(keyId);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.KEY_NOT_FOUND.getMessage()))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return optionalKeyDTO.get();
    }

    @PutMapping("/add/{id_student}/{id_key}")
    @ResponseStatus(HttpStatus.OK)
    public KeyDTO giveKey(@PathVariable Integer id_student, @PathVariable Integer id_key) {
        Optional<Student> try_student = Optional.empty();
        try {
            try_student = studentService.findByID(id_student);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        Optional<KeyDTO> try_key = Optional.empty();
        try {
            try_key = keyService.findByIdAsDTO(id_key);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.KEY_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        if (try_key.isEmpty() || try_student.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (try_key.get().getStudent_id() != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessage.KEY_IN_STUDENT.getMessage());
        KeyCreateDTO keyCreateDTO = new KeyCreateDTO(try_student.get().getId(), false, try_key.get().getWashingMachine_id());

        KeyDTO keyDTO = null;
        try {
            keyDTO = keyService.update(id_key, keyCreateDTO);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.KEY_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        return keyDTO;
    }

    @PutMapping("/remove/{id_key}")
    @ResponseStatus(HttpStatus.OK)
    public KeyDTO returnKey(@PathVariable Integer id_key) {
        KeyDTO try_key = null;
        try {
            try_key = keyService.returnKey(id_key);
        }
        catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.KEY_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        return try_key;
    }

    @GetMapping("/student/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public KeyDTO getKeyByStudent(@PathVariable Integer student_id) {
        KeyDTO keyDTO = null;
        try {
            keyDTO = keyService.findByStudent(student_id);
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.STUDENT_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        return keyDTO;
    }

    @GetMapping("/wm/{washingMachine_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<KeyDTO> getKeyByWashingMachine(@PathVariable Integer washingMachine_id) {
        List<KeyDTO> keyDTO_list = Collections.emptyList();
        try {
            keyDTO_list = keyService.findByWashingMachineAndReturnStatus(washingMachine_id);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.WM_NOT_FOUND.getMessage())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        return keyDTO_list;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<KeyDTO> getAll() {
        return keyService.findAll();
    }

}


//Write two Get functions to get KeyByStudent and KeyByWashingMachine;

//return list of keyDTO in both, the only difference is one by student and one by washing machine
