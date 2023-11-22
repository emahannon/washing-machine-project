package cz.cvut.fit.si1.server.business.dto;

import java.util.Objects;

public class WashingMachineDto {
    private final Integer id;
    private final Boolean status;
    private final Integer floorNumber;
    private final Integer buildingNumber;

    public WashingMachineDto(Integer id, Boolean status, Integer floorNumber, Integer buildingNumber) {
        this.id = id;
        this.status = status;
        this.floorNumber = floorNumber;
        this.buildingNumber = buildingNumber;
    }

    public Integer getId() {
        return id;
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
        WashingMachineDto that = (WashingMachineDto) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(floorNumber, that.floorNumber) && Objects.equals(buildingNumber, that.buildingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, floorNumber, buildingNumber);
    }
}
