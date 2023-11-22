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

    public ReservationCreateDto(LocalDate createDate, Instant startTime, Instant endTime, Integer student_id, Integer washingMachine_id) {
        this.createDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.student_id = student_id;
        this.washingMachine_id = washingMachine_id;
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
        ReservationCreateDto that = (ReservationCreateDto) o;
        return createDate.equals(that.createDate) && startTime.equals(that.startTime) && endTime.equals(that.endTime) && student_id.equals(that.student_id) && washingMachine_id.equals(that.washingMachine_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createDate, startTime, endTime, student_id, washingMachine_id);
    }
}
