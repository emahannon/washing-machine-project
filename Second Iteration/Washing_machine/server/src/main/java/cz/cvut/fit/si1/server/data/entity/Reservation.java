package cz.cvut.fit.si1.server.data.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Table(name = "\"RESERVATION\"")
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reservation_id", nullable = false)
    private Integer id;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "washing_machine_id", nullable = false)
    private WashingMachine washingMachine;

    @ManyToOne
    @JoinColumn(name = "swap_req_id")
    private SwapRequest swapReq;

    public Reservation(@NonNull LocalDate createDate, @NonNull Instant startTime, @NonNull Instant endTime, @NonNull Student student, @NonNull WashingMachine washingMachine) {
        this.createdDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.student = student;
        this.washingMachine = washingMachine;
        this.swapReq = null;
    }

    public Reservation() {

    }

    public SwapRequest getSwapReq() {
        return swapReq;
    }

    public void setSwapReq(SwapRequest swapReq) {
        this.swapReq = swapReq;
    }

    public WashingMachine getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}