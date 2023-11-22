package cz.cvut.fit.si1.server.business.dto;

public class KeyCreateDTO {

    private final Integer student_id;
    private final boolean returnStatus;
    private final Integer washingMachine_id;

    /**
     * Constructor for the key create DTO.
     *
     * @param student_id        Integer that represents the id of the Student that has the Key or None if no one has it
     * @param returnStatus      Boolean that represents if the key is in the reception (True) or has been given to some Student (False)
     * @param washingMachine_id Integer that represents the id of the Washing Machine that the key belongs to
     */
    public KeyCreateDTO(Integer student_id,
                        boolean returnStatus,
                        Integer washingMachine_id) {
        this.student_id = student_id;
        this.returnStatus = returnStatus;
        this.washingMachine_id = washingMachine_id;
    }

    /**
     * Getter for washing machine ID
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
}
