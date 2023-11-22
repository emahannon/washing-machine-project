package cz.cvut.fit.si1.server.business.service.baseservice;

import cz.cvut.fit.si1.server.business.dto.SendSwapListForDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.business.service.BaseService;
import cz.cvut.fit.si1.server.data.entity.SwapRequest;

import java.util.List;

public interface ReservationBaseService<T, S, D, K> extends BaseService<T, S, D, K> {
    void updateSwapRequest(SwapRequestDto swapRequestDto) throws Exception;

    void deleteSwapRequest(Integer reservation_id) throws Exception;

    void addSwapRequest(K reservation_id, SwapRequest swapRequest) throws Exception;

    List<T> findByMachine(K WashingMachine_ID) throws Exception;

    List<T> findByStudent(K Student_ID) throws Exception;

    T findBySwapRequest(K SwapRequest_ID) throws Exception;

    List<SendSwapListForDto> findAllSwapRequestToStudent(K Student_ID);
}
