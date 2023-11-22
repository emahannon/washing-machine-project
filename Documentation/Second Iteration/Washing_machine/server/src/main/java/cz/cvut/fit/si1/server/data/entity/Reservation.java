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

    /**
     * Constructor for the Reservation class.
     *
     * @param createDate     Instance of the LocalDate Class of the date the reservation was created.
     * @param startTime      Instant representing the start time of the reservation.
     * @param endTime        Instant representing the end time of the reservation.
     * @param student        Instance of the Student Class that represents the student who made the reservation.
     * @param washingMachine Instance of the WashingMachine Class of the washing machine associated with the reservation.
     */
    public Reservation(@NonNull LocalDate createDate, @NonNull Instant startTime, @NonNull Instant endTime, @NonNull Student student, @NonNull WashingMachine washingMachine) {
        this.createdDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.student = student;
        this.washingMachine = washingMachine;
        this.swapReq = null;
    }

    /**
     * Default constructor for the Reservation class.
     */
    public Reservation() {

    }

    /**
     * Getter for the swap request object.
     *
     * @return swapReq - Instance of the SwapRequest Class associated with the reservation.
     */
    public SwapRequest getSwapReq() {
        return swapReq;
    }

    /**
     * Setter for the swap request objcet.
     *
     * @param swapReq Instance of the SwapRequest Class associated with the reservation.
     */
    public void setSwapReq(SwapRequest swapReq) {
        this.swapReq = swapReq;
    }

    /**
     * Getter for the washing machien objcet.
     *
     * @return washingMachine Instance of the WashingMachine Class of the machine associated with the reservation.
     */
    public WashingMachine getWashingMachine() {
        return washingMachine;
    }

    /**
     * Setter for the washing machine.
     *
     * @param washingMachine Instance of the WashingMachine Class of the machine associated with the reservation.
     */
    public void setWashingMachine(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    /**
     * Getter for the student objcet.
     *
     * @return student - Instance of the Student Class of the student associated with the reservation.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Setter for the student.
     *
     * @param student Instance of the Student Class of the student associated with the reservation.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Getter for the end time of the reservation.
     *
     * @return endTime - Instant that represents the end time of the reservation.
     */
    public Instant getEndTime() {
        return endTime;
    }

    /**
     * Setter for the end time of the reservation.
     *
     * @param endTime Instant that represents the end time of the reservation.
     */
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    /**
     * Gettter for the start time of the reservation.
     *
     * @return startTime - Instant that represents the start time of the reservation.
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * Setter for the start time of the reservation.
     *
     * @param startTime Instant that represents the start time of the reservation.
     */
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for the created date.
     * Returns a LocalDate object.
     *
     * @return createdData - Instance of the LocalDate Class that represents the date the reservation was created on.
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Setter for the created date.
     *
     * @param createdDate Instance of the LocalDate Class that represents the date the reservation was created on.
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Getter for the ID of the reservation.
     *
     * @return id - Integer that represents the id of the reservation.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of teh reservation.
     *
     * @param id Integer that represents the id of the reservation.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}