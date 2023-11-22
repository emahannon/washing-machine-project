package cz.cvut.fit.si1.server.business.dto;

import java.time.Instant;
import java.util.Objects;

public class SwapRequestDto {
    private final Integer id;
    private final String reason;
    private final Integer student_id;
    private final Instant timeReq;
    private final Boolean status;

    public SwapRequestDto(Integer id, String reason, Integer student_id, Instant timeReq, Boolean status) {
        this.id = id;
        this.reason = reason;
        this.student_id = student_id;
        this.timeReq = timeReq;
        this.status = status;
    }

    public Integer getId() {
        return id;
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

    public Boolean getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SwapRequestDto)) return false;
        SwapRequestDto that = (SwapRequestDto) o;
        return id == that.id &&
                reason.equals(that.reason) &&
                student_id.equals(that.student_id) &&
                timeReq.equals(that.timeReq) &&
                status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
