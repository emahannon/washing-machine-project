package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.RoomHistory;
import cz.cvut.fit.si1.server.data.repository.baseentityrepo.BaseRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomHistoryRepository extends BaseRoomRepository<RoomHistory, Integer> {
    List<RoomHistory> findRoomHistoriesById_StudentId(Integer student_id);
}
