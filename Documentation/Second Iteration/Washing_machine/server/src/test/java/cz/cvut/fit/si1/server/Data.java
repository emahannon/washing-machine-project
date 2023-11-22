package cz.cvut.fit.si1.server;

import cz.cvut.fit.si1.server.business.dto.*;
import cz.cvut.fit.si1.server.data.entity.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class Data {
    public final static Student student = new Student(true, 3,
            "TestName", "TestLastName");
    public final static StudentDto studentDto = new StudentDto(1, true, 3,
            "TestName", "TestLastName");
    public final static StudentCreateDto studentCreateDto = new StudentCreateDto(true, 3,
            "TestName", "TestLastName");
    public final static StudentRoomDto studentRoomDto = new StudentRoomDto(3,
            "TestName", "TestLastName", 444, "Name building");

    public final static Instant timeReq = Instant.now();

    public final static SwapRequest swapRequest = new SwapRequest(timeReq,
            "Test reason", false, student);
    public final static SwapRequestDto swapRequestDto = new SwapRequestDto(1,
            "Test reason", studentDto.getId(), timeReq, false);
    public final static SwapRequestCreateDto swapRequestCreateDto = new SwapRequestCreateDto(
            "Test reason", studentDto.getId(), timeReq);

    public final static Student student1 = new Student(true, 3,
            "TestName1", "TestLastName1");

    public final static Student student2 = new Student(true, 3,
            "TestName2", "TestLastName2");

    public final static Student student3 = new Student(true, 3,
            "TestName3", "TestLastName3");

    public final static StudentDto studentDto1 = new StudentDto(2, true, 3,
            "TestName1", "TestLastName1");

    public final static StudentDto studentDto2 = new StudentDto(3, true, 3,
            "TestName2", "TestLastName2");

    public final static SwapRequest swapRequest1 = new SwapRequest(timeReq,
            "Test reason1", false, student1);

    public final static SwapRequest swapRequest2 = new SwapRequest(timeReq,
            "Test reason2", false, student2);

    public final static SwapRequestDto swapRequestDto1 = new SwapRequestDto(2,
            "Test reason1", student1.getId(), timeReq, false);

    public final static SwapRequestDto swapRequestDto2 = new SwapRequestDto(3,
            "Test reason2", student2.getId(), timeReq, false);

    public final static Building building = new Building("b1", "123 street", 1, 1);

    public final static Building building1 = new Building("b4", "123 road", 12, 3);

    public final static WashingMachine washingMachine = new WashingMachine(true, 2, building);

    public final static WashingMachine washingMachine1 = new WashingMachine(true, 3, building);

    public final static WashingMachine washingMachine2 = new WashingMachine(true, 1, building1);

    public final static WashingMachineCreateDto washingMachineCDto = new WashingMachineCreateDto(true, 2, 1);

    public final static WashingMachineCreateDto washingMachineCDto1 = new WashingMachineCreateDto(true, 3, 1);

    public final static WashingMachineCreateDto washingMachineCDto2 = new WashingMachineCreateDto(true, 1, 2);

    public final static WashingMachineDto washingMachineDto = new WashingMachineDto(1, true, 2, 1);

    public final static WashingMachineDto washingMachineDto1 = new WashingMachineDto(2, true, 3, 1);

    public final static WashingMachineDto washingMachineDto2 = new WashingMachineDto(3, true, 1, 2);

    public final static List<WashingMachine> wmList = List.of(washingMachine, washingMachine1);

    public final static List<WashingMachineDto> wmListDTO = List.of(washingMachineDto, washingMachineDto1);

    public final static List<WashingMachineCreateDto> wmListCDTO = List.of(washingMachineCDto, washingMachineCDto1);

    public final static Reservation reservation = new Reservation(LocalDate.of(2021, 7, 9), Instant.from(LocalDate.of(2021, 7, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()), Instant.from(LocalDate.of(2021, 7, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()), student, washingMachine);

    public final static ReservationDto reservationDto = new ReservationDto(1, LocalDate.of(2021, 7, 9), Instant.from(LocalDate.of(2021, 7, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()), Instant.from(LocalDate.of(2021, 7, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()), 1, 1);

    public final static ReservationCreateDto reservationCreateDto = new ReservationCreateDto(LocalDate.of(2021, 7, 9), Instant.from(LocalDate.of(2021, 7, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()), Instant.from(LocalDate.of(2021, 7, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()), 1, 1);

    public final static Key key = new Key(student, true, washingMachine);
    public final static Key key1 = new Key(student1, true, washingMachine);
    public final static Key key2 = new Key(student2, true, washingMachine);

    public final static KeyDTO keyDTO = new KeyDTO(1, studentDto.getId(), true, washingMachineDto.getId());
    public final static KeyDTO keyDTO1 = new KeyDTO(2, studentDto1.getId(), true, washingMachineDto.getId());
    public final static KeyDTO keyDTO2 = new KeyDTO(3, studentDto2.getId(), true, washingMachineDto.getId());

    public final static KeyCreateDTO keyCreateDTO = new KeyCreateDTO(studentDto.getId(), true, washingMachineDto.getId());
    public final static KeyCreateDTO keyCreateDTO1 = new KeyCreateDTO(studentDto1.getId(), true, washingMachineDto.getId());
    public final static KeyCreateDTO keyCreateDTO2 = new KeyCreateDTO(studentDto2.getId(), true, washingMachineDto.getId());

}
