package cz.cvut.fit.si1.server.business.service;

import cz.cvut.fit.si1.server.business.dto.StudentCreateDto;
import cz.cvut.fit.si1.server.business.dto.StudentDto;
import cz.cvut.fit.si1.server.business.dto.StudentRoomDto;
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
public class StudentService {
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final RoomHistoryRepository roomHistoryRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, RoomRepository roomRepository, RoomHistoryRepository roomHistoryRepository) {
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.roomHistoryRepository = roomHistoryRepository;
    }

    public StudentDto create(StudentCreateDto studentCreateDto) throws Exception {
        if (studentCreateDto.getFirstName() == null || studentCreateDto.getLastName() == null) {
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());
        }
        Student student = new Student(studentCreateDto.getCurrentOccupent(),
                studentCreateDto.getPeneltyPoints(),
                studentCreateDto.getFirstName(),
                studentCreateDto.getLastName());

        return toDTO(studentRepository.save(student));
    }

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

        return toDTO(student);
    }

    public List<StudentDto> findAll() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDto> res = new ArrayList<>();
        for (Student student : studentList) {
            res.add(toDTO(student));
        }
        return res;
    }

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

    public Optional<Student> findByID(int id) throws Exception {
        return Optional.ofNullable(studentRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage())));
    }

    public Optional<StudentDto> findByIDAsDTO(int id) {
        return toDTO(studentRepository.findById(id));
    }

    public void deleteById(int id) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        } else {
            studentRepository.deleteById(id);
        }
    }

    private Optional<StudentDto> toDTO(Optional<Student> optionalStudent) {
        if (optionalStudent.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalStudent.get()));
    }

    private StudentDto toDTO(Student student) {
        return new StudentDto(student.getId(),
                student.getCurrentOccupent(),
                student.getPeneltyPoints(),
                student.getFirstName(),
                student.getLastName());
    }

}
