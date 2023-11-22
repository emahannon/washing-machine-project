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

    public Key() {
    }

    public Key(Student student,
               Boolean returnStatus,
               WashingMachine washingMachine) {
        this.student = student;
        this.returnStatus = returnStatus;
        this.washingMachine = washingMachine;
    }

    public WashingMachine getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    public Boolean getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
