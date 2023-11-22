package cz.cvut.fit.si1.server.business.dto;

public class StudentRoomDto {
    private final Integer peneltyPoints;
    private final String firstName;
    private final String lastName;
    private final Integer roomNumber;
    private final String nameBuilding;

    /**
     * Constructor for the student room DTO.
     *
     * @param peneltyPoints Integer that represents the number of penelty points the Student has
     * @param firstName     String that represents the first name of the Student
     * @param lastName      String that represents the last name of the Student
     * @param roomNumber    Integer that represents the number of the room where the Student lives
     * @param nameBuilding  String that represents the name of the building where the Student lives
     */
    public StudentRoomDto(Integer peneltyPoints, String firstName, String lastName, Integer roomNumber, String nameBuilding) {
        this.peneltyPoints = peneltyPoints;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;
        this.nameBuilding = nameBuilding;
    }

    /**
     * Getter for the penelty points.
     *
     * @return peneltyPoints - Integer that represents the number of penelty points the Student has
     */
    public Integer getPeneltyPoints() {
        return peneltyPoints;
    }

    /**
     * Getter for the first name of the student.
     *
     * @return firstName - String that represents the first name of the Student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the last name of the student.
     *
     * @return lastName - String that represents the last name of the Student
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for the room number.
     *
     * @return roomNumber - Integer that represents the number of the room where the Student lives
     */
    public Integer getRoomNumber() {
        return roomNumber;
    }

    /**
     * Getter for the name of the building.
     *
     * @return nameBuilding - String that represents the name of the building where the Student lives
     */
    public String getNameBuilding() {
        return nameBuilding;
    }
}
