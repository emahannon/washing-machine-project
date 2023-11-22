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

    public ReservationDto(Integer id, LocalDate createDate, Instant startTime, Instant endTime, Integer student_id, Integer washingMachine_id) {
        this.id = id;
        this.createDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.student_id = student_id;
        this.washingMachine_id = washingMachine_id;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public Integer getWashingMachine_id() {
        return washingMachine_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto that = (ReservationDto) o;
        return id.equals(that.id) && createDate.equals(that.createDate) && startTime.equals(that.startTime) && endTime.equals(that.endTime) && student_id.equals(that.student_id) && washingMachine_id.equals(that.washingMachine_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, startTime, endTime, student_id, washingMachine_id);
    }
}
