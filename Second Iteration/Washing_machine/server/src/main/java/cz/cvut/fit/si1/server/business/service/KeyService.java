package cz.cvut.fit.si1.server.business.service;

import cz.cvut.fit.si1.server.business.dto.KeyCreateDTO;
import cz.cvut.fit.si1.server.business.dto.KeyDTO;
import cz.cvut.fit.si1.server.data.entity.Key;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.KeyRepository;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KeyService {
    private final KeyRepository keyRepository;
    private final StudentService studentService;
    private final WashingMachineService washingMachineService;

    @Autowired
    public KeyService(KeyRepository keyRepository, StudentService studentService, WashingMachineService washingMachineService) {
        this.keyRepository = keyRepository;
        this.studentService = studentService;
        this.washingMachineService = washingMachineService;
    }

    public KeyDTO create(KeyCreateDTO keyCreateDTO) throws Exception {
        if (keyCreateDTO.getReturnStatus() == null || keyCreateDTO.getWashingMachine_id() == null) {
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());
        }
        return toDTO(keyRepository.save(new Key(
                studentService.findByID(keyCreateDTO
                                .getStudent_id())
                        .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())),
                keyCreateDTO.getReturnStatus(),
                washingMachineService.findByID(keyCreateDTO.getWashingMachine_id())
                        .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())))));
    }

    @Transactional
    public KeyDTO update(Integer id, KeyCreateDTO keyCreateDTO) throws Exception {
        Optional<Key> optionalKey = keyRepository.findById(id);
        if (optionalKey.isEmpty())
            throw new Exception(ErrorMessage.KEY_NOT_FOUND.getMessage());

        Key key = optionalKey.get();
        key.setStudent(studentService.findByID(keyCreateDTO.getStudent_id())
                .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())));
        key.setReturnStatus(keyCreateDTO.getReturnStatus());
        key.setWashingMachine(washingMachineService.findByID(keyCreateDTO.getWashingMachine_id())
                .orElseThrow(() -> new Exception(ErrorMessage.WM_NOT_FOUND.getMessage())));
        return toDTO(key);
    }

    @Transactional
    public KeyDTO returnKey(Integer key_id) throws Exception {
        Key key;
        Optional<Key> optionalKey = keyRepository.findById(key_id);
        if (optionalKey.isEmpty())
            throw new Exception(ErrorMessage.KEY_NOT_FOUND.getMessage());
        else {
            key = optionalKey.get();
            key.setReturnStatus(true);
            key.setStudent(null);
        }

        return toDTO(key);
    }

    public List<KeyDTO> findAll() {
        return keyRepository.findAll().
                stream().
                map(this::toDTO).
                collect(Collectors.toList());
    }

    public List<Key> findByIds(List<Integer> ids) {
        return keyRepository.findAllById(ids);
    }

    public Optional<Key> findById(int id) {
        return keyRepository.findById(id);
    }

    public Optional<KeyDTO> findByIdAsDTO(int id) throws Exception {
        return Optional.of(toDTO(keyRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessage.KEY_NOT_FOUND.getMessage()))));
    }

    public KeyDTO findByStudent(Integer student_id) throws Exception {
        Optional<Student> optionalStudent = studentService.findByID(student_id);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }
        Key student_key = keyRepository.findByStudent(optionalStudent.get());

        return toDTO(student_key);
    };

    public List<KeyDTO> findByWashingMachineAndReturnStatus(Integer washingMachine_id) throws Exception {
        Optional<WashingMachine> optionalWashingMachine = washingMachineService.findByID(washingMachine_id);
        if (optionalWashingMachine.isEmpty()) {
            throw new Exception(ErrorMessage.WM_NOT_FOUND.getMessage());
        }
        List<Key> washingMachine_list = keyRepository.findByWashingMachine(optionalWashingMachine.get());

        return washingMachine_list.stream().filter(Key::getReturnStatus).map(this::toDTO).collect(Collectors.toList());
    }

    public void deleteById(int id) throws Exception {
        Optional<Key> optionalKey = keyRepository.findById(id);
        if (optionalKey.isEmpty())
            throw new Exception(ErrorMessage.KEY_NOT_FOUND.getMessage());
        else
            keyRepository.deleteById(id);
    }

    private Optional<KeyDTO> toDTO(Optional<Key> optionalKey) {
        if (optionalKey.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalKey.get()));
    }

    private KeyDTO toDTO(Key key) {
        Integer student_id;
        if (key.getStudent() == null) {
            student_id = null;
        } else {
            student_id = key.getStudent().getId();
        }
        return new KeyDTO(key.getId(),
                student_id,
                key.getReturnStatus(),
                key.getWashingMachine().getId());
    }
}
