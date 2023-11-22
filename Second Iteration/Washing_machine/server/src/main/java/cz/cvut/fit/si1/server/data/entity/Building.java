package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;

@Table(name = "\"BUILDING\"")
@Entity
public class Building {
    public Building(String name, String address, Integer number, Integer location) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.location = location;
    }

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

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}