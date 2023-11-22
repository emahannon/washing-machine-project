package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "\"ROOM_HISTORY\"")
@Entity
public class RoomHistory {
    @EmbeddedId
    @GeneratedValue
    private RoomHistoryId id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public RoomHistoryId getId() {
        return id;
    }

    public void setId(RoomHistoryId id) {
        this.id = id;
    }
}