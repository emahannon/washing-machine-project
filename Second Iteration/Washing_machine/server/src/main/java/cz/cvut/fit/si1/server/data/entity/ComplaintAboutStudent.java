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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public Instant getCloedTime() {
        return cloedTime;
    }

    public void setCloedTime(Instant cloedTime) {
        this.cloedTime = cloedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}