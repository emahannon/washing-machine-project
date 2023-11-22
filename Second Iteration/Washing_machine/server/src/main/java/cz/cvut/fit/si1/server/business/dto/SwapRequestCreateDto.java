package cz.cvut.fit.si1.server.business.dto;

import java.time.Instant;
import java.util.Objects;

public class SwapRequestCreateDto {
    private final String reason;
    private final Integer student_id;
    private final Instant timeReq;

    public SwapRequestCreateDto(String reason, Integer student_id, Instant timeReq) {
        this.reason = reason;
        this.student_id = student_id;
        this.timeReq = timeReq;
    }

    public String getReason() {
        return reason;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public Instant getTimeReq() {
        return timeReq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SwapRequestCreateDto)) return false;
        SwapRequestCreateDto that = (SwapRequestCreateDto) o;
        return reason.equals(that.reason) &&
                student_id == that.student_id &&
                timeReq.equals(that.timeReq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason, student_id);
    }

}
