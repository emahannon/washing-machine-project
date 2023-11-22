package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WashingMachineRepository extends JpaRepository<WashingMachine, Integer> {
    /**
     * @param building_id Integer that represents the building id.
     * @return List{@literal <WashingMachine>} - List of all washing machines in a certain building.
     */
    List<WashingMachine> findWashingMachinesByBuilding_Id(Integer building_id);
}
