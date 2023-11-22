package cz.cvut.fit.si1.server.business.dto;

import java.util.Objects;

public class WashingMachineCreateDto {

    private final Boolean status;
    private final Integer floorNumber;
    private final Integer buildingNumber;

    /**
     * Constructor for the WashingMachineCreate DTO.
     *
     * @param status         Boolean that represents if the Washing Machine is working (True) or not (False)
     * @param floorNumber    Integer that represents the number of the floor where the Washing Machine is
     * @param buildingNumber Integer that represents the number of the building where the Washing Machine is
     */
    public WashingMachineCreateDto(Boolean status, Integer floorNumber, Integer buildingNumber) {
        this.status = status;
        this.floorNumber = floorNumber;
        this.buildingNumber = buildingNumber;
    }

    /**
     * Getter for the status of the machine.
     *
     * @return status - Boolean that represents if the Washing Machine is working (True) or not (False)
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Getter for the floor number the machine is located on.
     *
     * @return floorNumber - Integer that represents the number of the floor where the Washing Machine is
     */
    public Integer getFloorNumber() {
        return floorNumber;
    }

    /**
     * Getter for the building number the machine is located in.
     *
     * @return buildingNumber - Integer that represents the number of the building where the Washing Machine is
     */
    public Integer getBuildingNumber() {
        return buildingNumber;
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
        if (o == null || getClass() != o.getClass()) return false;
        WashingMachineCreateDto that = (WashingMachineCreateDto) o;
        return status.equals(that.status) && floorNumber.equals(that.floorNumber) && buildingNumber.equals(that.buildingNumber);
    }

    /**
     * Creates hash code for the object.
     *
     * @return int - hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(status, floorNumber, buildingNumber);
    }
}
