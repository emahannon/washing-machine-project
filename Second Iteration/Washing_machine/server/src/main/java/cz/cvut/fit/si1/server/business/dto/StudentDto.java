package cz.cvut.fit.si1.server.business.dto;


import java.util.Objects;

public class StudentDto {
    private final Integer id;
    private final Boolean currentOccupent;
    private final Integer peneltyPoints;
    private final String firstName;
    private final String lastName;

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

    public Integer getId() {
        return id;
    }

    public Boolean getCurrentOccupent() {
        return currentOccupent;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, currentOccupent, peneltyPoints);
    }

}
