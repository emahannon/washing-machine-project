package cz.cvut.fit.si1.server.data.repository.baseentityrepo;

import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import cz.cvut.fit.si1.server.data.repository.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseReservationRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {
    List<T> findByWashingMachine(WashingMachine washingMachine);

    List<T> findByStudent(Student entity);

    List<T> findByStudent_Id(ID id);

    Optional<T> findReservationBySwapReq_Id(ID id);

    void deleteByEndTimeBefore(Instant instant);

}
