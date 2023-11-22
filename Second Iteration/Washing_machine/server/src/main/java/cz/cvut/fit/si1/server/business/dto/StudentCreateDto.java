package cz.cvut.fit.si1.server.business.dto;


import java.util.Objects;

public class StudentCreateDto {
    private final Boolean currentOccupent;
    private final Integer peneltyPoints;
    private final String firstName;
    private final String lastName;

    public StudentCreateDto(Boolean currentOccupent,
                            Integer peneltyPoints,
                            String firstName,
                            String lastName) {
        this.currentOccupent = currentOccupent;
        this.peneltyPoints = peneltyPoints;
        this.firstName = firstName;
        this.lastName = lastName;
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
        if (!(o instanceof StudentCreateDto)) return false;
        StudentCreateDto that = (StudentCreateDto) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                currentOccupent == that.currentOccupent &&
                peneltyPoints.equals(that.peneltyPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, currentOccupent, peneltyPoints);
    }

}

