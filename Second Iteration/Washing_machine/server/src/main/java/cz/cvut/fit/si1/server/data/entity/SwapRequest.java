package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "\"SWAP_REQUEST\"")
@Entity
public class SwapRequest {
    @Id
    @Column(name = "swap_req_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "time_req", nullable = false)
    private Instant timeReq;

    @Column(name = "reason", length = 500)
    private String reason;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public SwapRequest() {
    }

    public SwapRequest(Instant timeReq, String reason, Boolean status, Student student) {
        this.timeReq = timeReq;
        this.reason = reason;
        this.status = status;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Instant getTimeReq() {
        return timeReq;
    }

    public void setTimeReq(Instant timeReq) {
        this.timeReq = timeReq;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}