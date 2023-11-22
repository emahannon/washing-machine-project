package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "\"COMPLAINT_ABOUT_STUDENT\"")
@Entity
public class ComplaintAboutStudent {
    @Id
    @Column(name = "complaint_student_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "created_time", nullable = false)
    private Instant createdTime;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "cloed_time")
    private Instant cloedTime;

    @ManyToOne
    @JoinColumn(name = "receptionist_id")
    private Receptionist receptionist;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    /**
     * Getter for the student object.
     *
     * @return student Instance of the Student Class associated with the complaint.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Setter for the student.
     *
     * @param student Instance of the Student Class associated with the complaint.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Getter for the receptionist object.
     *
     * @return receptionist - Instance of the Receptionist Class associated with the complaint.
     */
    public Receptionist getReceptionist() {
        return receptionist;
    }

    /**
     * Setter for the receptionist.
     *
     * @param receptionist Instance of the Receptionist Class associated with the complaint.
     */
    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    /**
     * Getter for the cloed time.
     *
     * @return cloedTime Instance of the Instant Class associated with the complaint.
     */
    public Instant getCloedTime() {
        return cloedTime;
    }

    /**
     * Setter for the cloed time.
     *
     * @param cloedTime Instance of the Instant Class associated with the complaint.
     */
    public void setCloedTime(Instant cloedTime) {
        this.cloedTime = cloedTime;
    }

    /**
     * Getter for the status of the complaint.
     *
     * @return status - String that represents the status of the complaint.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for the status of the complaint.
     *
     * @param status String that represents the status of the complaint.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for the description of the complaint.
     *
     * @return description - String that represents the description of the complaint.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the complaint.
     *
     * @param description String that represents the description of the complaint.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the created time
     *
     * @return createdTime - Instant that represents the created time of the complaint.
     */
    public Instant getCreatedTime() {
        return createdTime;
    }

    /**
     * Setter for the created time.
     *
     * @param createdTime Instant that represents the created time of the complaint.
     */
    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Getter for the ID.
     *
     * @return id - Integer that represents the id of the complaint.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID.
     *
     * @param id Integer that represents the id of the complaint.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
