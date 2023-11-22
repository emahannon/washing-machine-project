package cz.cvut.fit.si1.server.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class ReservationCreateDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private final LocalDate createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private final Instant startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private final Instant endTime;
    private final Integer student_id;
    private final Integer washingMachine_id;

    /**
     * Constructor for the reservation create DTO.
     *
     * @param createDate        Date that represents the time when the Reservation was created
     * @param startTime         Instant that represents the time the Reservation starts
     * @param endTime           Instant that represents the time the Reservation ends
     * @param student_id        Integer that represents the id of the Student that has made the Reservation
     * @param washingMachine_id Integer that represents the id of the Washing Machine on which the Reservation is made
     */
    public ReservationCreateDto(LocalDate createDate, Instant startTime, Instant endTime, Integer student_id, Integer washingMachine_id) {
        this.createDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.student_id = student_id;
        this.washingMachine_id = washingMachine_id;
    }

    /**
     * Getter for date.
     *
     * @return createDate - Date that represents the time when the Reservation was created
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * Getter for the start time.
     *
     * @return startTime - Instant that represents the time the Reservation starts
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * Getter for the end time.
     *
     * @return endTime - Instant that represents the time the Reservation ends
     */
    public Instant getEndTime() {
        return endTime;
    }

    /**
     * Getter for the student ID
     *
     * @return student_id - Integer that represents the id of the Student that has made the Reservation
     */
    public Integer getStudent_id() {
        return student_id;
    }

    /**
     * Getter for the washing machine ID
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
        ReservationCreateDto that = (ReservationCreateDto) o;
        return createDate.equals(that.createDate) && startTime.equals(that.startTime) && endTime.equals(that.endTime) && student_id.equals(that.student_id) && washingMachine_id.equals(that.washingMachine_id);
    }

    /**
     * Creates hash code for the object.
     *
     * @return int - hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(createDate, startTime, endTime, student_id, washingMachine_id);
    }
}
