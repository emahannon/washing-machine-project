package cz.cvut.fit.si1.server.data.repository.baseentityrepo;

import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.repository.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseSwapRequestRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {
    List<T> findByStudent(Student student);
}
