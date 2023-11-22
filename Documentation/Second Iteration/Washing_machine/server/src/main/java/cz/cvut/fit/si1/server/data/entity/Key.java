package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;


@Table(name = "\"KEY\"")
@Entity
public class Key {
    @Id
    @Column(name = "key_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "return_status", nullable = false)
    private Boolean returnStatus = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "washing_machine_id", nullable = false)
    private WashingMachine washingMachine;

    /**
     * Default constructor for the Key class.
     */
    public Key() {
    }

    /**
     * Constructor for the key class.
     *
     * @param student
     * @param returnStatus
     * @param washingMachine
     */
    public Key(Student student,
               Boolean returnStatus,
               WashingMachine washingMachine) {
        this.student = student;
        this.returnStatus = returnStatus;
        this.washingMachine = washingMachine;
    }

    /**
     * Getter for the washing machine object.
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
     * Getter for the return status of the key.
     *
     * @return returnStatus - Boolean that represents the return status of the key.
     */
    public Boolean getReturnStatus() {
        return returnStatus;
    }

    /**
     * Setter for the return status of the key.
     *
     * @param returnStatus Boolean that represents the return status of the key.
     */
    public void setReturnStatus(Boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    /**
     * Getter for the student object.
     *
     * @return student - Instance of the Student Class.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Setter for the student object.
     *
     * @param student Instance of the Student Class
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Getter for the ID.
     *
     * @return id - Integer that represents the id of the key.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID.
     *
     * @param id Integer that represents the id of the key.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
