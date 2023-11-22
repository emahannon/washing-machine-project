package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.SwapRequest;
import cz.cvut.fit.si1.server.data.repository.baseentityrepo.BaseReservationRepository;
import cz.cvut.fit.si1.server.data.repository.baseentityrepo.BaseSwapRequestRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwapRequestRepository extends BaseSwapRequestRepository<SwapRequest, Integer> {
    List<SwapRequest> findByStudent(Student student);
}
