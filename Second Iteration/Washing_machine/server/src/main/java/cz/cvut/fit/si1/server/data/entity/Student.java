package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;

@Table(name = "\"STUDENT\"")
@Entity
public class Student {
    @Id
    @Column(name = "student_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "current_occupent", nullable = false)
    private Boolean currentOccupent = false;

    @Column(name = "penelty_points", nullable = false)
    private Integer peneltyPoints;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    public Student() {
    }

    public Student(Boolean currentOccupent, Integer peneltyPoints, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentOccupent = currentOccupent;
        this.peneltyPoints = peneltyPoints;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getPeneltyPoints() {
        return peneltyPoints;
    }

    public void setPeneltyPoints(Integer peneltyPoints) {
        this.peneltyPoints = peneltyPoints;
    }

    public Boolean getCurrentOccupent() {
        return currentOccupent;
    }

    public void setCurrentOccupent(Boolean currentOccupent) {
        this.currentOccupent = currentOccupent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}