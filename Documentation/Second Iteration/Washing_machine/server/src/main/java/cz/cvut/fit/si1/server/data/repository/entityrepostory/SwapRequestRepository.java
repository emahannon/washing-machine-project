package cz.cvut.fit.si1.server.data.repository.entityrepostory;

import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.SwapRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwapRequestRepository extends JpaRepository<SwapRequest, Integer> {
    /**
     * Interface to find a list of swap requests through search by a student object.
     *
     * @param student Student object that represents the student that we want to fetch his SwapRequests
     * @return List{@literal <SwapRequest>} - List of all the SwapRequestDto belonging to the Student
     */
    List<SwapRequest> findByStudent(Student student);
}
