package cz.cvut.fit.si1.server.business.service.baseservice;

import cz.cvut.fit.si1.server.business.service.BaseService;

import java.util.List;

public interface WashingMachineBaseService<T, S, D, K> extends BaseService<T, S, D, K> {
    List<T> findByBuildingValidateDto(Integer Building_Id);
}
