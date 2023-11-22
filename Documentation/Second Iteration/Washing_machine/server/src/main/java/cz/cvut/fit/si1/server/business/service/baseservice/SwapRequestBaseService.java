package cz.cvut.fit.si1.server.business.service.baseservice;

import cz.cvut.fit.si1.server.business.service.BaseService;

import java.util.List;

public interface SwapRequestBaseService<T, S, D, K> extends BaseService<T, S, D, K> {
    void approveSwapRequest(K swapRequestDto) throws Exception;

    List<T> findByStudent(K student_id) throws Exception;

    void deleteByIdAndReservationId(K reservation_id, K id) throws Exception;

}
