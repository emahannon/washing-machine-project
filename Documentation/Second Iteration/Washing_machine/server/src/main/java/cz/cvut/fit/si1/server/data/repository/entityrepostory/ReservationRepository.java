package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.Reservation;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    /**
     * Interface to find a list of reservations through search by washing machine object.
     *
     * @param washingMachine Instance of the WashingMachine class.
     * @return List{@literal <Reservation>} - List of reservations associated with a single washing machine.
     */
    List<Reservation> findByWashingMachine(WashingMachine washingMachine);

    /**
     * Interface to find a list of reservations through search by student object.
     *
     * @param student Instance of the Student class.
     * @return List{@literal <Reservation>} - List of reservations associated with a single student.
     */
    List<Reservation> findByStudent(Student student);

    /**
     * Interface to find a list of reservations through search by student id.
     *
     * @param integer Integer associated with the student id.
     * @return List{@literal <Reservation>} - List of reservations associated with a student id.
     */
    List<Reservation> findByStudent_Id(Integer integer);

    /**
     * Interface to find reservation through search by swap request id.
     *
     * @param swapRequest_id Integer representing the id of the swap request.
     * @return Optional Reservation - Reservation associated with the swap request.
     */
    Optional<Reservation> findReservationBySwapReq_Id(Integer swapRequest_id);

    /**
     * Interface to delete a reservation through search by time.
     *
     * @param instant Instance of the Instant class.
     */
    void deleteByEndTimeBefore(Instant instant);
}
