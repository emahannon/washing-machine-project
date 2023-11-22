package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.Building;
import cz.cvut.fit.si1.server.data.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends BaseRepository<Building, Integer> {
}
