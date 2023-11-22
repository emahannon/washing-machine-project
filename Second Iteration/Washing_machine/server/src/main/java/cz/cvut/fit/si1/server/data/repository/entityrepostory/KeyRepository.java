package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.Key;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import cz.cvut.fit.si1.server.data.repository.baseentityrepo.BaseKeyRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyRepository extends BaseKeyRepository<Key, Integer> {
    Key findByStudent(Student student);

    List<Key> findByWashingMachine(WashingMachine washingmachine);
}