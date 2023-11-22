package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;

@Table(name = "\"BUILDING\"")
@Entity
public class Building {

    /**
     * Constructor for the Building class.
     *
     * @param name     String that represents the name of the building.
     * @param address  String that represents the address of the building.
     * @param number   Integer that represents the number of the building.
     * @param location Integer that represents the location of the building.
     */
    public Building(String name, String address, Integer number, Integer location) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.location = location;
    }

    /**
     * Default constructor for the Building class.
     */
    public Building() {
    }

    @Id
    @Column(name = "building_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "location", nullable = false)
    private Integer location;

    /**
     * Getter for the location of the building.
     *
     * @return location - Integer that represents the location of the building.
     */
    public Integer getLocation() {
        return location;
    }

    /**
     * Setter for the location of the building.
     *
     * @param location Integer that represents the location of the building.
     */
    public void setLocation(Integer location) {
        this.location = location;
    }

    /**
     * Getter for the number of the building.
     *
     * @return number - Integer that represents the number of the building.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Setter for the number of the building.
     *
     * @param number Integer that represents the number of the building.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Getter for the address of the building.
     *
     * @return address - String that represents the address of the building.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for the address of the building.
     *
     * @param address String that represents the address of the building.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for the name of the building.
     *
     * @return name - String that represents the name of the building.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the building.
     *
     * @param name String that represents the name of the building.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the ID of the building.
     *
     * @return id - Integer that represents the id of the building.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of the building.
     *
     * @param id Integer that represents the id of the building.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}