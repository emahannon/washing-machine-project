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

    /**
     * Default constructor for the Student class.
     */
    public Student() {
    }

    /**
     * Constructor for the Student class.
     *
     * @param currentOccupent Boolean that represents if the student is a current occupant in the dorms or not.
     * @param peneltyPoints   Integer that represents the number of penelty points the studnet has.
     * @param firstName       String that represents the first name of the student.
     * @param lastName        String that represents the last name of the student.
     */
    public Student(Boolean currentOccupent, Integer peneltyPoints, String firstName, String lastName) {
        this.currentOccupent = currentOccupent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.peneltyPoints = peneltyPoints;
    }

    /**
     * Getter for the last name of the student.
     *
     * @return lastName - String that represents the last name of the student.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the last name of the student.
     *
     * @param lastName String that represents the last name of the student.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the first name of the student.
     *
     * @return firstName - String that represents the first name of the student.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the first name of the student.
     *
     * @param firstName String that represents the first name of the student.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the number of penelty points of the student.
     *
     * @return peneltyPoints - Integer that represents the number of penelty points the student has.
     */
    public Integer getPeneltyPoints() {
        return peneltyPoints;
    }

    /**
     * Setter for the number of penelty points of the student.
     *
     * @param peneltyPoints Integer that represents the number of penelty points the student has.
     */
    public void setPeneltyPoints(Integer peneltyPoints) {
        this.peneltyPoints = peneltyPoints;
    }

    /**
     * Getter for the current occupent.
     * Returns whether or not the student is a current occupant in the dorms or not.
     *
     * @return currentOccupent - Boolean that represents the student being an occupant in the dorms or not.
     */
    public Boolean getCurrentOccupent() {
        return currentOccupent;
    }

    /**
     * Setter for the current occupent variable.
     * Tells us whether or not the student is a current occupant in the dorms or not.
     *
     * @param currentOccupent Boolean that represents the student being an occupant in the dorms or not.
     */
    public void setCurrentOccupent(Boolean currentOccupent) {
        this.currentOccupent = currentOccupent;
    }

    /**
     * Getter for the ID of the student.
     *
     * @return id - Integer that represents the id of the student.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of the student.
     *
     * @param id Integer that represents the id of the student.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}