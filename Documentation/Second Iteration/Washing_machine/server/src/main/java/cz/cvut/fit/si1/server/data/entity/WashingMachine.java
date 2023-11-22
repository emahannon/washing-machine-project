package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;

@Table(name = "\"WASHING_MACHINE\"")
@Entity
public class WashingMachine {
    @Id
    @Column(name = "washing_machine_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @Column(name = "floor_bumber", nullable = false)
    private Integer floorBumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    /**
     * Constructor for the washing machine object.
     *
     * @param status      Boolean that represents the status of the machine.
     * @param floorNumber Integer that represents the number of the floor that the machine is located on.
     * @param byId        Instance of the Building Class that represents the building the machine is located in.
     */
    public WashingMachine(Boolean status, Integer floorNumber, Building byId) {
        this.status = status;
        this.floorBumber = floorNumber;
        this.building = byId;
    }

    /**
     * Default constructor for the washing machine object.
     */
    public WashingMachine() {
    }

    /**
     * Getter for the building object (where the washing machine is located in).
     *
     * @return building - Instance of the Building Class that represents the building the machine is located in.
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Setter for the building that the washing machine is located in.
     *
     * @param building Instance of the Building Class that represents the building the machine is located in.
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * Getter for the floor number the machine is located on.
     *
     * @return floorBumber - Integer that represents the number of the floor the machine is located on.
     */
    public Integer getFloorBumber() {
        return floorBumber;
    }

    /**
     * Setter for the floor number the machine is located on.
     *
     * @param floorBumber Integer that represents the number of the floor the machine is located on.
     */
    public void setFloorBumber(Integer floorBumber) {
        this.floorBumber = floorBumber;
    }

    /**
     * Getter for the status of the washing machine.
     *
     * @return status - Boolean that represents the status of the washing machine.
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Setter for the status of the washing machine.
     *
     * @param status Boolean that represents the status of the washign machine.
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Getter for the ID of the washing machine.
     *
     * @return id Integer that represents the id of the washing machine.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of the washing machine.
     *
     * @param id Integer that represents the id of the washing machine.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}