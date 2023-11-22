package cz.cvut.fit.si1.server.data.entity;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "\"PENELTIES\"")
@Entity
public class Penelty {
    @Id
    @Column(name = "penelties_id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "points_before", nullable = false)
    private Integer pointsBefore;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "complaint_student_id", nullable = false)
    private ComplaintAboutStudent complaintStudent;

    /**
     * Getter for the complaint about student object.
     *
     * @return complaintStudent - Instance of the ComplaintAboutStudent Class.
     */
    public ComplaintAboutStudent getComplaintStudent() {
        return complaintStudent;
    }

    /**
     * Setter for the complaint about student object.
     *
     * @param complaintStudent Instance of the ComplaintAboutStudent Class.
     */
    public void setComplaintStudent(ComplaintAboutStudent complaintStudent) {
        this.complaintStudent = complaintStudent;
    }

    /**
     * Getter for the start date of the penelty.
     *
     * @return startDate - Instant that represents the start date of the penelty.
     */

    public Instant getStartDate() {
        return startDate;
    }

    /**
     * Setter for the start date of the penelty.
     *
     * @param startDate Instant that represents the start date of the penelty.
     */
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for the points before the penelty.
     *
     * @return pointsBefore - Integer that represents the points before the penelty.
     */
    public Integer getPointsBefore() {
        return pointsBefore;
    }

    /**
     * Setter for the points before the penelty.
     *
     * @param pointsBefore Integer that represents the points before the penelty.
     */
    public void setPointsBefore(Integer pointsBefore) {
        this.pointsBefore = pointsBefore;
    }

    /**
     * Getter for the ID of the penelty.
     *
     * @return id - Integer that represents the id of the penelty.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of the penelty.
     *
     * @param id Integer that represents the id of the penelty.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}