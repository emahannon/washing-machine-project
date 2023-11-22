package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import cz.cvut.fit.si1.server.data.repository.baseentityrepo.BaseWashingMachineRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WashingMachineRepository extends BaseWashingMachineRepository<WashingMachine, Integer> {
    List<WashingMachine> findWashingMachinesByBuilding_Id(Integer building_id);
}
