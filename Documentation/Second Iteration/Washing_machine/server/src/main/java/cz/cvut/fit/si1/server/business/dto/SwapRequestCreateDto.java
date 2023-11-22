package cz.cvut.fit.si1.server.business.dto;

import java.time.Instant;
import java.util.Objects;


public class SwapRequestCreateDto {
    private final String reason;
    private final Integer student_id;
    private final Instant timeReq;

    /**
     * Constructor for the SwapRequestCreate DTO.
     *
     * @param reason     String that tells the reason why the swap request is submitted
     * @param student_id Integer that represents the id of the Student that wants to make the swap
     * @param timeReq    Instant that represents the time the SwapRequest was created
     */
    public SwapRequestCreateDto(String reason, Integer student_id, Instant timeReq) {
        this.reason = reason;
        this.student_id = student_id;
        this.timeReq = timeReq;
    }

    /**
     * Getter for the reason for creating a swap request.
     *
     * @return reason - String that tells the reason why the swap request is submitted
     */
    public String getReason() {
        return reason;
    }

    /**
     * Getter for the student ID
     *
     * @return student_id - Integer that represents the id of the Student that wants to make the swap
     */
    public Integer getStudent_id() {
        return student_id;
    }

    /**
     * Getter for the time when the swap was requested.
     *
     * @return timeReq - Instant that represents the time the SwapRequest was created
     */
    public Instant getTimeReq() {
        return timeReq;
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
        if (!(o instanceof SwapRequestCreateDto)) return false;
        SwapRequestCreateDto that = (SwapRequestCreateDto) o;
        return reason.equals(that.reason) &&
                student_id == that.student_id &&
                timeReq.equals(that.timeReq);
    }

    /**
     * Creates hash code for the object.
     *
     * @return int - hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(reason, student_id);
    }

}
