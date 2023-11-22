package cz.cvut.fit.si1.server.business.service.serviceimpl;

import cz.cvut.fit.si1.server.business.dto.KeyCreateDTO;
import cz.cvut.fit.si1.server.business.dto.KeyDTO;
import cz.cvut.fit.si1.server.business.service.baseservice.KeyBaseService;
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
public class KeyService implements KeyBaseService<KeyDTO, KeyCreateDTO, Key, Integer> {
    private final KeyRepository keyRepository;
    private final StudentService studentService;
    private final WashingMachineService washingMachineService;

    /**
     * Constructor for the KeyService class.
     * @param keyRepository Instance of the KeyRepository Interface
     * @param studentService    Instance of the StudentService Class
     * @param washingMachineService Instance of the WashingMachineService Class
     */
    @Autowired
    public KeyService(KeyRepository keyRepository, StudentService studentService, WashingMachineService washingMachineService) {
        this.keyRepository = keyRepository;
        this.studentService = studentService;
        this.washingMachineService = washingMachineService;
    }

    /**
     * Method to create the key.
     * @param keyCreateDTO  KeyCreateDTO object with the information to create a new Key
     * @return key DTO - KeyDTO object resulting from the creation of the new Key
     * @throws Exception    Thrown if status or washingMachine_id are null in the KeyCreateDTO object. Also, if the Student or the WashingMachine, which ids are in the KeyCreateDto object, are not found.
     */
    public KeyDTO create(KeyCreateDTO keyCreateDTO) throws Exception {
        if (keyCreateDTO.getReturnStatus() == null || keyCreateDTO.getWashingMachine_id() == null) {
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());
        }
        return toDto(keyRepository.save(new Key(
                studentService.findById(keyCreateDTO
                                .getStudent_id())
                        .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())),
                keyCreateDTO.getReturnStatus(),
                washingMachineService.findById(keyCreateDTO.getWashingMachine_id())
                        .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())))));
    }

    /**
     * Method to update the key.
     * @param id    Integer that represents the id of the Key that is going to be updated
     * @param keyCreateDTO  KeyCreateDTO object with the information to update the Key
     * @return key DTO -  KeyDTO object resulting from the creation of the new Key
     * @throws Exception    Thrown if the Student or the WashingMachine, which ids are in the KeyCreateDto object, or the Key, which id is a parameters, are not found.
     */
    @Transactional
    public KeyDTO update(Integer id, KeyCreateDTO keyCreateDTO) throws Exception {
        Optional<Key> optionalKey = keyRepository.findById(id);
        if (optionalKey.isEmpty())
            throw new Exception(ErrorMessage.KEY_NOT_FOUND.getMessage());

        Key key = optionalKey.get();
        key.setStudent(studentService.findById(keyCreateDTO.getStudent_id())
                .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())));
        key.setReturnStatus(keyCreateDTO.getReturnStatus());
        key.setWashingMachine(washingMachineService.findById(keyCreateDTO.getWashingMachine_id())
                .orElseThrow(() -> new Exception(ErrorMessage.WM_NOT_FOUND.getMessage())));
        return toDto(key);
    }

    /**
     * Method for returning the Ley to the receptionist by the key_id.
     * @param key_id    Integer that represents the id of the Key
     * @return KeyDTO - KeyDTO object with the updated status
     * @throws Exception Thrown if the Key, which id is a parameter, is not found
     */
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

        return toDto(key);
    }

    /**
     * Returns a list of all the key DTOs.
     * @return List{@literal <KeyDTO>} - List of all the KeyDto
     */
    public List<KeyDTO> findAll() {
        return keyRepository.findAll().
                stream().
                map(this::toDto).
                collect(Collectors.toList());
    }

    /**
     * Finds a list of keys by a list of IDs.
     * @param ids   List{@literal <Integer>} that represent the ids of the Keys
     * @return List{@literal <Key>} - List of the Key which ids where in the parameter
     */
    public List<Key> findByIds(List<Integer> ids) {
        return keyRepository.findAllById(ids);
    }

    /**
     * Finds the key by the ID.
     * @param id    Integer that represent the id of the Key
     * @return Key - Key which id is the one sent in the parameter
     */
    public Optional<Key> findById(Integer id) {
        return keyRepository.findById(id);
    }

    /**
     * Finds key DTO by the ID of the key.
     * @param id     Integer that represent the id of the Key
     * @return keyDTO - KeyDTO which id is the one sent in the parameter
     * @throws Exception    Thrown if the Key, which id is the parameter, is not found
     */
    public Optional<KeyDTO> findByIdAsDto(Integer id) throws Exception {
        return Optional.of(toDto(keyRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessage.KEY_NOT_FOUND.getMessage()))));
    }

    /**
     * Getter method that finds a key by the student ID.
     * @param student_id    Integer that represent the id of the Student that has the Key
     * @return keyDTO -  KeyDTO of the Key that the Student has
     * @throws Exception Thrown if the Student, which id is the parameter, is not found
     */
    public KeyDTO findByStudent(Integer student_id) throws Exception {
        Optional<Student> optionalStudent = studentService.findById(student_id);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }
        Key student_key = keyRepository.findByStudent(optionalStudent.get());

        return toDto(student_key);
    }

    ;    /**
     * Finds a key DTO by the washing machine ID and returns the key status.
     * @param washingMachine_id Integer that represent the id of the Washing Machine that has that Key
     * @return List{@literal <KeyDTO>} - List{@literal <KeyDTO>} that has all the KeyDTOs of the available Keys for that Washing Machine
     * @throws Exception Thrown if the Washing Machine, which id is the parameter, is not found
     */
    public List<KeyDTO> findByWashingMachineAndReturnStatus(Integer washingMachine_id) throws Exception {
        Optional<WashingMachine> optionalWashingMachine = washingMachineService.findById(washingMachine_id);
        if (optionalWashingMachine.isEmpty()) {
            throw new Exception(ErrorMessage.WM_NOT_FOUND.getMessage());
        }
        List<Key> washingMachine_list = keyRepository.findByWashingMachine(optionalWashingMachine.get());

        return washingMachine_list.stream().filter(Key::getReturnStatus).map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Deletes key by passing ID.
     * @param id    Integer that represent the id of the Key we want to delete
     * @throws Exception    Thrown if the Key, which id is the parameter, is not found
     */
    public void deleteById(Integer id) throws Exception {
        Optional<Key> optionalKey = keyRepository.findById(id);
        if (optionalKey.isEmpty())
            throw new Exception(ErrorMessage.KEY_NOT_FOUND.getMessage());
        else
            keyRepository.deleteById(id);
    }

    /**
     * Converts an optional key to an optional DTO.
     * @param optionalKey   Optional{@literal <Key>} that we want to convert to DTO
     * @return Optional{@literal <KeyDTO>} - Optional{@literal <KeyDTO>} resulting from the conversion
     */
    public Optional<KeyDTO> toDto(Optional<Key> optionalKey) {
        if (optionalKey.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDto(optionalKey.get()));
    }

    /**
     * Converts a key object to key DTO
     * @param key   Key that we want to convert to DTO
     * @return KeyDTO - KeyDTO resulting from the conversion
     */
    public KeyDTO toDto(Key key) {
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
