package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "\"COMPLAINT_ABOUT_WM\"", indexes = {
        @Index(name = "\"IXFK_COMPLAINT_ABOUT_WM_RECEPTIONIST\"", columnList = "receptionist_id")
})
@Entity
public class ComplaintAboutWm {
    @Id
    @Column(name = "complaint_wm_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "created_time", nullable = false)
    private Instant createdTime;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "closed_time")
    private Instant closedTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "washing_machine_id", nullable = false)
    private WashingMachine washingMachine;

    @ManyToOne
    @JoinColumn(name = "receptionist_id")
    private Receptionist receptionist;

    /**
     * Getter for the receptionist object.
     *
     * @return receptionist - Instance of the Receptionist Class.
     */
    public Receptionist getReceptionist() {
        return receptionist;
    }

    /**
     * Setter for the receptionist.
     *
     * @param receptionist Instance of the Receptionist Class.
     */
    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    /**
     * Getter for the washing machien object.
     *
     * @return washingMachine - Instance of the WashingMachine Class.
     */
    public WashingMachine getWashingMachine() {
        return washingMachine;
    }

    /**
     * Setter for the washing machine.
     *
     * @param washingMachine Instance of the WashingMachine Class.
     */
    public void setWashingMachine(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    /**
     * Getter for the reservation object.
     *
     * @return reservation - Instance of the Reservation Class.
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Setter for the reservation.
     *
     * @param reservation Instance of the Reservation Class.
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Getter for the closed time.
     *
     * @return closedTime - Instant that represents the closed time of the complaint.
     */
    public Instant getClosedTime() {
        return closedTime;
    }

    /**
     * Setter for the closed time.
     *
     * @param closedTime Instant that represents the closed time of the complaint.
     */
    public void setClosedTime(Instant closedTime) {
        this.closedTime = closedTime;
    }

    /**
     * Getter for the status of the complaint.
     *
     * @return status - String that represents the status of the complaint.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for the status of the complaint.
     *
     * @param status String that represents the status of the complaint.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for the description of the complaint.
     *
     * @return description - String that represents the description of the complaint.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the complaint.
     *
     * @param description String that represents the description of the complaint.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the created time.
     *
     * @return createdTime - Instant that represents the created time of the complaint.
     */
    public Instant getCreatedTime() {
        return createdTime;
    }

    /**
     * Setter for the created time.
     *
     * @param createdTime Instant that represents the created time of the complaint.
     */
    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Getter for the ID.
     *
     * @return id - Integer that represents the ID of the complaint.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID.
     *
     * @param id Integer that represents the id of the complaint.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}