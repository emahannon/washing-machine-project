package cz.cvut.fit.si1.server.business.service.baseservice;

import cz.cvut.fit.si1.server.business.service.BaseService;

import java.util.List;

public interface KeyBaseService<T, S, D, K> extends BaseService<T, S, D, K> {
    T findByStudent(K id_student) throws Exception;

    List<T> findByWashingMachineAndReturnStatus(K id_wm) throws Exception;

    T returnKey(K id_key) throws Exception;
}
