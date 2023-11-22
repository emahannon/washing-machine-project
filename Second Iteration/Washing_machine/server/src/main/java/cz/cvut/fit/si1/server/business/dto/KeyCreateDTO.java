package cz.cvut.fit.si1.server.business.dto;

public class KeyCreateDTO {

    private final Integer student_id;
    private final boolean returnStatus;
    private final Integer washingMachine_id;

    public KeyCreateDTO(Integer student_id,
                        boolean returnStatus,
                        Integer washingMachine_id) {
        this.student_id = student_id;
        this.returnStatus = returnStatus;
        this.washingMachine_id = washingMachine_id;
    }

    public Integer getWashingMachine_id() {
        return washingMachine_id;
    }

    public Boolean getReturnStatus() {
        return returnStatus;
    }

    public Integer getStudent_id() {
        return student_id;
    }
}
