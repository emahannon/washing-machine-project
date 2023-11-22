package cz.cvut.fit.si1.server.business.dto;

public class StudentRoomDto {
    private final Integer peneltyPoints;
    private final String firstName;
    private final String lastName;
    private final Integer roomNumber;
    private final String nameBuilding;

    public StudentRoomDto(Integer peneltyPoints, String firstName, String lastName, Integer roomNumber, String nameBuilding) {
        this.peneltyPoints = peneltyPoints;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;
        this.nameBuilding = nameBuilding;
    }

    public Integer getPeneltyPoints() {
        return peneltyPoints;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }
}
