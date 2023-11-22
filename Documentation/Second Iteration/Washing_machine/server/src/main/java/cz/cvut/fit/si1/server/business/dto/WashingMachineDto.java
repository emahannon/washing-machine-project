package cz.cvut.fit.si1.server.business.dto;

import java.util.Objects;

public class WashingMachineDto {
    private final Integer id;
    private final Boolean status;
    private final Integer floorNumber;
    private final Integer buildingNumber;

    /**
     * Constructor for the washing machine DTO.
     *
     * @param id             Integer that represents the id of the Washing Machine
     * @param status         Boolean that represents if the Washing Machine is working (True) or not (False)
     * @param floorNumber    Integer that represents the number of the floor where the Washing Machine is
     * @param buildingNumber Integer that represents the number of the building where the Washing Machine is
     */
    public WashingMachineDto(Integer id, Boolean status, Integer floorNumber, Integer buildingNumber) {
        this.id = id;
        this.status = status;
        this.floorNumber = floorNumber;
        this.buildingNumber = buildingNumber;
    }

    /**
     * Getter for the ID of the machine.
     *
     * @return id - Integer that represents the id of the Washing Machine
     */
    public Integer getId() {
        return id;
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
        WashingMachineDto that = (WashingMachineDto) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(floorNumber, that.floorNumber) && Objects.equals(buildingNumber, that.buildingNumber);
    }

    /**
     * Creates hash code for the object.
     *
     * @return int - hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, status, floorNumber, buildingNumber);
    }
}
