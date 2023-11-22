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

    /**
     * Getter for the room ID.
     *
     * @return roomId - Integer that represents the room id.
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * Setter for the room ID.
     *
     * @param roomId Integer that represents the room id.
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * Getter for the student ID.
     *
     * @return studentId - Integer that represents the student id.
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * Setter for the student ID.
     *
     * @param studentId Integer that represents the student id.
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * Generates hash code for the object.
     *
     * @return int that represents the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId, roomId);
    }

    /**
     * Compares each attribute of both objects to see if they are equal or not.
     * Returns true if all parameters of both compared objects are the same.
     * Returns true if both objects are instances of the came class.
     * Otherwise returns false.
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoomHistoryId entity = (RoomHistoryId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.roomId, entity.roomId);
    }
}