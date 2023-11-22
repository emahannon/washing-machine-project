package cz.cvut.fit.si1.server.data.repository.baseentityrepo;

import cz.cvut.fit.si1.server.data.repository.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRoomRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {
    List<T> findRoomHistoriesById_StudentId(ID id);
}
