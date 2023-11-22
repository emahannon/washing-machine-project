package cz.cvut.fit.si1.server.business.dto;

import java.util.Objects;

public class CreateWashingMachineDto {

    private final Boolean status;
    private final Integer floorNumber;
    private final Integer buildingNumber;

    public CreateWashingMachineDto(Boolean status, Integer floorNumber, Integer buildingNumber) {
        this.status = status;
        this.floorNumber = floorNumber;
        this.buildingNumber = buildingNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateWashingMachineDto that = (CreateWashingMachineDto) o;
        return status.equals(that.status) && floorNumber.equals(that.floorNumber) && buildingNumber.equals(that.buildingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, floorNumber, buildingNumber);
    }
}
