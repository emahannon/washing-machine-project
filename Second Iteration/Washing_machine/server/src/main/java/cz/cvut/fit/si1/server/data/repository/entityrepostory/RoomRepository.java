package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.Room;
import cz.cvut.fit.si1.server.data.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends BaseRepository<Room, Integer> {
}
