package cz.cvut.fit.si1.server.business.dto;

import java.util.Objects;

public class KeyDTO {

    private final Integer id;
    private final Integer student_id;
    private final boolean returnStatus;
    private final Integer washingMachine_id;

    public KeyDTO(Integer id,
                  Integer student_id,
                  boolean returnStatus,
                  Integer washingMachine_id) {
        this.id = id;
        this.student_id = student_id;
        this.returnStatus = returnStatus;
        this.washingMachine_id = washingMachine_id;
    }

    public Integer getId() {
        return id;
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
