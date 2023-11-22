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

    /**
     * Default constructor for the SwapRequest class.
     */
    public SwapRequest() {
    }

    /**
     * Constructor for the SwapRequest class.
     *
     * @param timeReq Instant that represents the time the SwapRequest was created
     * @param reason  String that tells the reason why the swap request is submitted
     * @param status  Boolean that represents if the swap is approved (True) or not (False)
     * @param student Student object that represents the Student that wants to make the swap
     */
    public SwapRequest(Instant timeReq, String reason, Boolean status, Student student) {
        this.timeReq = timeReq;
        this.reason = reason;
        this.status = status;
        this.student = student;
    }

    /**
     * Getter for the student object.
     *
     * @return student - Student object that represents the Student that wants to make the swap
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Setter for the student.
     *
     * @param student Student object that represents the Student that wants to make the swap
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Getter for the status of the swap request.
     *
     * @return status - Boolean that represents if the swap is approved (True) or not (False)
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Setter for the status of the swap request.
     *
     * @param status Boolean that represents if the swap is approved (True) or not (False)
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Getter for the reason for making the swap request.
     *
     * @return reason - String that tells the reason why the swap request is submitted
     */
    public String getReason() {
        return reason;
    }

    /**
     * Setter for the reason for making the swap request.
     *
     * @param reason String that tells the reason why the swap request is submitted
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Getter for the time requested to swap.
     *
     * @return timeReq - Instant that represents the time the SwapRequest was created
     */
    public Instant getTimeReq() {
        return timeReq;
    }

    /**
     * Setter for the time requested to swap.
     *
     * @param timeReq Instant that represents the time the SwapRequest was created
     */
    public void setTimeReq(Instant timeReq) {
        this.timeReq = timeReq;
    }

    /**
     * Getter for the ID of the swap request.
     *
     * @return id - Integer that represents the id of the SwapRequest
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of the swap request.
     *
     * @param id Integer that represents the id of the SwapRequest
     */
    public void setId(Integer id) {
        this.id = id;
    }
}