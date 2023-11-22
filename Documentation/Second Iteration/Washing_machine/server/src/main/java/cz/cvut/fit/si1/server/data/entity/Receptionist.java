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

    /**
     * Getter for the complaint washing machine ID.
     *
     * @return complaintWmId - Integer that represents the complaint washing machine id.
     */
    public Integer getComplaintWmId() {
        return complaintWmId;
    }

    /**
     * Setter for the complaint washing machine ID.
     *
     * @param complaintWmId Integer that represents the complaint washing machine id.
     */
    public void setComplaintWmId(Integer complaintWmId) {
        this.complaintWmId = complaintWmId;
    }

    /**
     * Getter for the building object.
     *
     * @return building - Instance of the Building Class associated with where the receptionist works.
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Setter for the building.
     *
     * @param building Instance of the Building Class associated with where the receptionist works.
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * Getter for the last name of the receptionist.
     *
     * @return lastName - String that represents the last name of the receptionist.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the last name of the receptionist.
     *
     * @param lastName String that represents the last name of the receptionist.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the first name of the receptionist.
     *
     * @return firstName - String that represents the first name of the receptionist.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the first name of the receptionist.
     *
     * @param firstName String that represents the first name of the receptionist.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the receptionist ID.
     *
     * @return id - Integer that represents the id of the receptionist.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the receptionist ID.
     *
     * @param id Integer that represents the id of the receptionist.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}