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

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public WashingMachine getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Instant getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(Instant closedTime) {
        this.closedTime = closedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}