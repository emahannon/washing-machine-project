package cz.cvut.fit.si1.server.data.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoomHistoryId implements Serializable {
    private static final long serialVersionUID = -1759298043743820676L;
    @Column(name = "student_id", nullable = false)
    private Integer studentId;
    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, roomId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoomHistoryId entity = (RoomHistoryId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.roomId, entity.roomId);
    }
}