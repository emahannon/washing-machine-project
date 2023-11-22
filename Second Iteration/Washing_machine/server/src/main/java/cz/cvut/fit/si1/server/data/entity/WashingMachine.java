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

    public WashingMachine(Boolean status, Integer floorNumber, Building byId) {
        this.status = status;
        this.floorBumber = floorNumber;
        this.building = byId;
    }

    public WashingMachine() {
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Integer getFloorBumber() {
        return floorBumber;
    }

    public void setFloorBumber(Integer floorBumber) {
        this.floorBumber = floorBumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}