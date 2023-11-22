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

    /**
     * Getter for the building object.
     *
     * @return building - Instance of the Building Class that represents the building the room is located in.
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Setter for the building.
     *
     * @param building Instance of the Building Class that represents the building the room is located in.
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * Getter for the number of the room.
     *
     * @return number - Integer that represents the numbers of the room.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Setter for the number of the room.
     *
     * @param number Integer that represents the number of the room.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Getter for the ID of the room.
     *
     * @return id - Integer that represents the id of the room.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of the room.
     *
     * @param id Integer that represents the id of the room.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}