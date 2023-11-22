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

    /**
     * Getter for the end date.
     * Returns a LocalDate object.
     *
     * @return endData - Instance of the LocalDate Class that stores the end date of the room reservation.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Setter for the end date.
     *
     * @param endDate Instance of the LocalDate Class that stores the end date of the room reservation.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for the start date.
     * Returns a LocalDate object.
     *
     * @return startDate - Instance of the LocalDate Class that stores the start date of the room reservation.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Setter for the start date.
     *
     * @param startDate Instance of the LocalDate Class that stores the start date of the room reservation.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for the ID.
     * Returns a RoomHistoryId object.
     *
     * @return id - Instance of the RoomHistoryId Class that stores the id of the room history.
     */
    public RoomHistoryId getId() {
        return id;
    }

    /**
     * Setter for the ID.
     *
     * @param id Instance of the RoomHistoryId Class that stores the id of the room history.
     */
    public void setId(RoomHistoryId id) {
        this.id = id;
    }
}