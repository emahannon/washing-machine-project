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

    public ComplaintAboutStudent getComplaintStudent() {
        return complaintStudent;
    }

    public void setComplaintStudent(ComplaintAboutStudent complaintStudent) {
        this.complaintStudent = complaintStudent;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Integer getPointsBefore() {
        return pointsBefore;
    }

    public void setPointsBefore(Integer pointsBefore) {
        this.pointsBefore = pointsBefore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}