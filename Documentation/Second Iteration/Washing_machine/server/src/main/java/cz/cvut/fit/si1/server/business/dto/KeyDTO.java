package cz.cvut.fit.si1.server.business.dto;

import java.util.Objects;

public class KeyDTO {

    private final Integer id;
    private final Integer student_id;
    private final boolean returnStatus;
    private final Integer washingMachine_id;

    /**
     * Constructor for the key DTO.
     *
     * @param id
     * @param student_id        Integer that represents the id of the Student that has the Key or None if no one has it
     * @param returnStatus      Boolean that represents if the key is in the reception (True) or has been given to some Student (False)
     * @param washingMachine_id Integer that represents the id of the Washing Machine that the key belongs to
     */
    public KeyDTO(Integer id,
                  Integer student_id,
                  boolean returnStatus,
                  Integer washingMachine_id) {
        this.id = id;
        this.student_id = student_id;
        this.returnStatus = returnStatus;
        this.washingMachine_id = washingMachine_id;
    }

    /**
     * Getter for the ID.
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for the waching machine ID.
     *
     * @return washingMachine_id - Integer that represents the id of the Washing Machine that the key belongs to
     */
    public Integer getWashingMachine_id() {
        return washingMachine_id;
    }

    /**
     * Getter for the return status.
     *
     * @return returnStatus - Boolean that represents if the key is in the reception (True) or has been given to some Student (False)
     */
    public Boolean getReturnStatus() {
        return returnStatus;
    }

    /**
     * Getter for the student ID.
     *
     * @return student_id - Integer that represents the id of the Student that has the Key or None if no one has it
     */
    public Integer getStudent_id() {
        return student_id;
    }

    /**
     * Compares each attribute of both objects to see if they are equal or not.
     * Returns true if all parameters of both compared objects are the same.
     * Returns true if both objects are instances of the came class.
     * Otherwise returns false.
     *
     * @param o Object being compared with
     * @return Boolean - True if they are equal, False if they are not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyDTO)) return false;
        KeyDTO that = (KeyDTO) o;
        return id == that.id &&
                returnStatus == that.returnStatus &&
                student_id.equals(that.student_id) &&
                washingMachine_id.equals(that.washingMachine_id);
    }

    /**
     * Creates hash code for the object.
     *
     * @return int - hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
