package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.RoomHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomHistoryRepository extends JpaRepository<RoomHistory, Integer> {

    /**
     * Interface to find a list of room history through search by student ID.
     *
     * @param student_id Integer that represents the student id.
     * @return List{@literal <RoomHistory>} - List of room histories associated with a single student id.
     */
    List<RoomHistory> findRoomHistoriesById_StudentId(Integer student_id);
}
