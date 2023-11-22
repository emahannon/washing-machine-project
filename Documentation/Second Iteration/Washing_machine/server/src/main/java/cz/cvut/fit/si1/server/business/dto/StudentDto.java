package cz.cvut.fit.si1.server.business.dto;


import java.util.Objects;

public class StudentDto {
    private final Integer id;
    private final Boolean currentOccupent;
    private final Integer peneltyPoints;
    private final String firstName;
    private final String lastName;

    /**
     * Constructor for the student DTO.
     *
     * @param id              Integer that represents the id of the Student
     * @param currentOccupent Boolean that represents if the Student is a current occupent of the dormitory (True) or not (False)
     * @param peneltyPoints   Integer that represents the number of penelty points the Student has
     * @param firstName       String that represents the first name of the Student
     * @param lastName        String that represents the last name of the Student
     */
    public StudentDto(Integer id,
                      Boolean currentOccupent,
                      Integer peneltyPoints,
                      String firstName,
                      String lastName) {
        this.id = id;
        this.currentOccupent = currentOccupent;
        this.peneltyPoints = peneltyPoints;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getter for ID.
     *
     * @return id - Integer that represents the id of the Student
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for the current occupent.
     *
     * @return currentOccupent - Boolean that represents if the Student is a current occupent of the dormitory (True) or not (False)
     */
    public Boolean getCurrentOccupent() {
        return currentOccupent;
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
     * Compares each attribute of both objects to see if they are equal or not.
     * Returns true if all parameters of both compared objects are the same.
     * Returns true if both objects are instances of the came class.
     * Otherwise returns false.
     *
     * @param o Object being compared with
     * @return Boolean - True if they are equal, False if they are not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDto)) return false;
        StudentDto that = (StudentDto) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                currentOccupent == that.currentOccupent &&
                id.equals(that.id) &&
                peneltyPoints.equals(that.peneltyPoints);
    }

    /**
     * Creates hash code for the object.
     *
     * @return int - hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, currentOccupent, peneltyPoints);
    }

}
