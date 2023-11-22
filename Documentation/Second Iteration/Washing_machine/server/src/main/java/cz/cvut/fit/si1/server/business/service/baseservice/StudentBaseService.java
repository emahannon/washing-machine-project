package cz.cvut.fit.si1.server.business.service.baseservice;

import cz.cvut.fit.si1.server.business.dto.StudentRoomDto;
import cz.cvut.fit.si1.server.business.service.BaseService;

import java.util.Optional;

public interface StudentBaseService<T, S, D, K> extends BaseService<T, S, D, K> {
    Optional<StudentRoomDto> findStudentRoomById(K id) throws Exception;

}
