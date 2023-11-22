package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.Key;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {
    Key findByStudent(Student student);

    /**
     * Interface to find a list of keys through a search by washing machine objcet.
     *
     * @param washingmachine Instance of the washing machine class.
     * @return List{@literal <Key>} - List of all the keys associated with a machine.
     */
    List<Key> findByWashingMachine(WashingMachine washingmachine);
}