package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;

@Table(name = "\"ROOM\"", indexes = {
        @Index(name = "\"IXFK_ROOM_BUILDING\"", columnList = "building_id")
})
@Entity
public class Room {
    @Id
    @Column(name = "room_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}