package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;

@Table(name = "\"RECEPTIONIST\"", indexes = {
        @Index(name = "\"IXFK_RECEPTIONIST_BUILDING\"", columnList = "building_id"),
        @Index(name = "\"IXFK_RECEPTIONIST_COMPLAINT_ABOUT_WM\"", columnList = "complaint_wm_id")
})
@Entity
public class Receptionist {
    @Id
    @Column(name = "receptionist_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @Column(name = "complaint_wm_id")
    private Integer complaintWmId;

    public Integer getComplaintWmId() {
        return complaintWmId;
    }

    public void setComplaintWmId(Integer complaintWmId) {
        this.complaintWmId = complaintWmId;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}