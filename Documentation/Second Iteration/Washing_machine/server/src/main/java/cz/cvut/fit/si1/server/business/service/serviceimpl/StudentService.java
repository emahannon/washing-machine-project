package cz.cvut.fit.si1.server.business.service.serviceimpl;

import cz.cvut.fit.si1.server.business.dto.StudentCreateDto;
import cz.cvut.fit.si1.server.business.dto.StudentDto;
import cz.cvut.fit.si1.server.business.dto.StudentRoomDto;
import cz.cvut.fit.si1.server.business.service.baseservice.StudentBaseService;
import cz.cvut.fit.si1.server.data.entity.Building;
import cz.cvut.fit.si1.server.data.entity.Room;
import cz.cvut.fit.si1.server.data.entity.RoomHistory;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.RoomHistoryRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.RoomRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.StudentRepository;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService implements
        StudentBaseService<StudentDto, StudentCreateDto, Student, Integer> {
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final RoomHistoryRepository roomHistoryRepository;

    /**
     * Constructor for the StudentService class.
     *
     * @param studentRepository     Instance of the StudentRepository Interface
     * @param roomRepository        Instance of the Room Repository Interface
     * @param roomHistoryRepository Instance of the RoomHistoryRepository Interface
     */
    @Autowired
    public StudentService(StudentRepository studentRepository, RoomRepository roomRepository, RoomHistoryRepository roomHistoryRepository) {
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.roomHistoryRepository = roomHistoryRepository;
    }

    /**
     * Method to create a student DTO.
     *
     * @param studentCreateDto StudentCreateDto object with the information to create a new Student
     * @return StudentDTO object resulting from the creation of the new Student
     * @throws Exception Thrown if the FirstName or the LastName is null
     */
    public StudentDto create(StudentCreateDto studentCreateDto) throws Exception {
        if (studentCreateDto.getFirstName() == null || studentCreateDto.getLastName() == null) {
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());
        }
        Student student = new Student(studentCreateDto.getCurrentOccupent(),
                studentCreateDto.getPeneltyPoints(),
                studentCreateDto.getFirstName(),
                studentCreateDto.getLastName());

        return toDto(studentRepository.save(student));
    }

    /**
     * Method to update the student DTO.
     *
     * @param id               Integer that represents the id of the Student that is going to be updated
     * @param studentCreateDto StudentCreateDto object with the information to update the Student
     * @return StudentDto - StudentDto object resulting from the creation of the new Student
     * @throws Exception Thrown if the Student, which id is a parameter, is not found
     */
    @Transactional
    public StudentDto update(Integer id, StudentCreateDto studentCreateDto) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }

        Student student = optionalStudent.get();
        student.setFirstName(studentCreateDto.getFirstName());
        student.setLastName(studentCreateDto.getLastName());
        student.setCurrentOccupent(studentCreateDto.getCurrentOccupent());
        student.setPeneltyPoints(studentCreateDto.getPeneltyPoints());

        return toDto(student);
    }

    /**
     * Finds and returns a list of all student DTOs.
     *
     * @return List{@literal <StudentDto>} - List of all the StudentDto
     */
    public List<StudentDto> findAll() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDto> res = new ArrayList<>();
        for (Student student : studentList) {
            res.add(toDto(student));
        }
        return res;
    }

    /**
     * Finds and returns a StudentRoomDto given the id
     *
     * @param id Integer that represents the id of the Student's Room
     * @return Optional{@literal <StudentRoomDto>} - Optional{@literal <StudentRoomDto>} of the Student's Room
     * @throws Exception Thrown if the Student or the Room is not found. Also, if the Student does no longer live in the dorm or number of rooms is different than 1
     */
    public Optional<StudentRoomDto> findStudentRoomById(Integer id) throws Exception {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty())
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        if (!studentOptional.get().getCurrentOccupent())
            throw new Exception(ErrorMessage.NOT_LIVE_IN_DORM.getMessage());

        List<RoomHistory> roomHistories = roomHistoryRepository.findRoomHistoriesById_StudentId(id)
                .stream().filter(roomHistory -> roomHistory.getEndDate() == null)
                .collect(Collectors.toList());
        if (roomHistories.size() != 1)
            throw new Exception(ErrorMessage.INVALID_NUMBER_OF_ROOMS.getMessage());

        Optional<Room> roomOptional = roomRepository.findById(roomHistories.get(0).getId().getRoomId());
        if (roomOptional.isEmpty())
            throw new Exception(ErrorMessage.ROOM_NOT_FOUND.getMessage());

        Optional<Building> buildingOptional = Optional.of(roomOptional.get().getBuilding());

        return Optional.of(new StudentRoomDto(studentOptional.get().getPeneltyPoints(),
                studentOptional.get().getFirstName(),
                studentOptional.get().getLastName(),
                roomOptional.get().getNumber(),
                buildingOptional.get().getName()));
    }

    /**
     * Finds and returns a student given the ID.
     *
     * @param id Integer that represents the d of the Student
     * @return Optional{@literal <Student>} -  Optional{@literal <Student>} object which id is the parameter
     * @throws Exception Thrown if the Student, which id is the parameter, is not found
     */
    public Optional<Student> findById(Integer id) throws Exception {
        return Optional.ofNullable(studentRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())));
    }

    /**
     * Finds and returns a student DTO given the ID.
     *
     * @param id Integer that represent the id of the Student
     * @return Optional{@literal <StudentDto>} - Optional{@literal <StudentDto>} object which id is the parameter
     */
    public Optional<StudentDto> findByIdAsDto(Integer id) {
        return toDto(studentRepository.findById(id));
    }

    /**
     * Finds and deletes a Student given the ID.
     *
     * @param id Integer that represents the id of the Student
     * @throws Exception Thrown id the Student, which id is the parameter, is not found
     */
    public void deleteById(Integer id) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        } else {
            studentRepository.deleteById(id);
        }
    }

    /**
     * Creates and returns a new student DTO based on a student. (Optional)
     *
     * @param optionalStudent Optional{@literal <Student>} to convert to DTO
     * @return Optional{@literal <StudentDto>} - Optional{@literal <StudentDto>} resulting from the conversion
     */
    public Optional<StudentDto> toDto(Optional<Student> optionalStudent) {
        if (optionalStudent.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDto(optionalStudent.get()));
    }

    /**
     * Creates and returns a new student DTO based on a student.
     *
     * @param student Student to convert to DTO
     * @return StudentDto - StudentDto resulting from the conversion
     */
    public StudentDto toDto(Student student) {
        return new StudentDto(student.getId(),
                student.getCurrentOccupent(),
                student.getPeneltyPoints(),
                student.getFirstName(),
                student.getLastName());
    }

}
