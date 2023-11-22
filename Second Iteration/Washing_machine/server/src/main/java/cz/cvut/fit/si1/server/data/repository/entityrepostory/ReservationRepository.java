package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.Reservation;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import cz.cvut.fit.si1.server.data.repository.baseentityrepo.BaseReservationRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends BaseReservationRepository<Reservation, Integer> {
    List<Reservation> findByWashingMachine(WashingMachine washingMachine);

    List<Reservation> findByStudent(Student student);

    List<Reservation> findByStudent_Id(Integer integer);

    Optional<Reservation> findReservationBySwapReq_Id(Integer swapRequest_id);

    void deleteByEndTimeBefore(Instant instant);
}
