package cz.cvut.fit.si1.server.service;

import cz.cvut.fit.si1.server.ServerApplication;
import cz.cvut.fit.si1.server.business.service.WashingMachineService;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.BuildingRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.WashingMachineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;

import java.util.Optional;

import static cz.cvut.fit.si1.server.Data.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ServerApplication.class)
public class washingMachineServiceTest {
    @Autowired
    WashingMachineService washingMachineService;

    @MockBean
    WashingMachineRepository washingMachineRepository;

    @MockBean
    BuildingRepository buildingRepository;

    @Test
    void create() throws Exception {
        ReflectionTestUtils.setField(washingMachine, "id", 1);
        ReflectionTestUtils.setField(washingMachineCDto,"buildingNumber",1);
        ReflectionTestUtils.setField(building,"id",1);

        BDDMockito.given(washingMachineRepository.save(any(WashingMachine.class))).willReturn(washingMachine);
        BDDMockito.given(buildingRepository.findById(any(Integer.class))).willReturn(Optional.of(building));

        Assertions.assertEquals(washingMachineDto, washingMachineService.create(washingMachineCDto));
    }

    @Test
    void findByBuildingValidated() throws Exception {
        ReflectionTestUtils.setField(washingMachine, "id", 1);
        ReflectionTestUtils.setField(washingMachine1, "id", 2);
        ReflectionTestUtils.setField(washingMachine2, "id", 3);
        ReflectionTestUtils.setField(building,"id",1);
        ReflectionTestUtils.setField(building1,"id",2);

        BDDMockito.given(washingMachineRepository.findWashingMachinesByBuilding_Id(1)).willReturn(wmList);
        BDDMockito.given(buildingRepository.findById(any(Integer.class))).willReturn(Optional.of(building));
        BDDMockito.given(buildingRepository.findById(any(Integer.class))).willReturn(Optional.of(building1));

        Assertions.assertEquals(washingMachineService.findByBuildingValidateDto(1), wmListDTO);
        Mockito.verify(washingMachineRepository, Mockito.atLeastOnce()).findWashingMachinesByBuilding_Id(1);

    }

}
