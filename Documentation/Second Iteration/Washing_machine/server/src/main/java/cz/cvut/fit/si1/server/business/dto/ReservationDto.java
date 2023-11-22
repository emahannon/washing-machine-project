package cz.cvut.fit.si1.server.business.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class ReservationDto {
    private final Integer id;
    private final LocalDate createDate;
    private final Instant startTime;
    private final Instant endTime;
    private final Integer student_id;
    private final Integer washingMachine_id;

    /**
     * Constructor for the reservation DTO
     *
     * @param id                Integer that represents id of the Reservation
     * @param createDate        Date that represents the time when the Reservation was created
     * @param startTime         Instant that represents the time the Reservation starts
     * @param endTime           Instant that represents the time the Reservation ends
     * @param student_id        Integer that represents the id of the Student that has made the Reservation
     * @param washingMachine_id Integer that represents the id of the Washing Machine on which the Reservation is made
     */
    public ReservationDto(Integer id, LocalDate createDate, Instant startTime, Instant endTime, Integer student_id, Integer washingMachine_id) {
        this.id = id;
        this.createDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.student_id = student_id;
        this.washingMachine_id = washingMachine_id;
    }

    /**
     * Getter for ID of the reservation.
     *
     * @return id - Integer that represents id of the Reservation
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for date of the reservation.
     *
     * @return createDate - Date that represents the time when the Reservation was created
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * Getter for the start time of the reservation.
     *
     * @return startTime - Instant that represents the time the Reservation starts
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * Getter for the end time of the reservation.
     *
     * @return endTime - Instant that represents the time the Reservation ends
     */
    public Instant getEndTime() {
        return endTime;
    }

    /**
     * Getter for the student ID.
     *
     * @return student_id - Integer that represents the id of the Student that has made the Reservation
     */
    public Integer getStudent_id() {
        return student_id;
    }

    /**
     * Getter for the washing machine ID.
     *
     * @return washingMachine_id - Integer that represents the id of the Washing Machine on which the Reservation is made
     */
    public Integer getWashingMachine_id() {
        return washingMachine_id;
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
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto that = (ReservationDto) o;
        return id.equals(that.id) && createDate.equals(that.createDate) && startTime.equals(that.startTime) && endTime.equals(that.endTime) && student_id.equals(that.student_id) && washingMachine_id.equals(that.washingMachine_id);
    }

    /**
     * Creates hash code for the object.
     *
     * @return int - hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, startTime, endTime, student_id, washingMachine_id);
    }
}
