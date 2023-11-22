package cz.cvut.fit.si1.server.business.service;

import cz.cvut.fit.si1.server.business.dto.WashingMachineCreateDto;
import cz.cvut.fit.si1.server.business.dto.WashingMachineDto;
import cz.cvut.fit.si1.server.data.entity.WashingMachine;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.BuildingRepository;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.WashingMachineRepository;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WashingMachineService {
    private final WashingMachineRepository washingMachineRepository;
    private final BuildingRepository buildingRepository;


    @Autowired
    public WashingMachineService(WashingMachineRepository washingMachineRepository, BuildingRepository buildingRepository) {
        this.washingMachineRepository = washingMachineRepository;
        this.buildingRepository = buildingRepository;
    }

    public Optional<WashingMachine> findByID(Integer washingMachine_id) {
        return washingMachineRepository.findById(washingMachine_id);
    }

    public WashingMachineDto create(WashingMachineCreateDto washingMachineCreateDto) throws Exception {
        if (washingMachineCreateDto.getBuildingNumber() == null || washingMachineCreateDto.getFloorNumber() == null) {
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());
        }

        return toDTO(washingMachineRepository.save(new WashingMachine(
                washingMachineCreateDto.getStatus(), washingMachineCreateDto.getFloorNumber(),
                buildingRepository.findById(washingMachineCreateDto.getBuildingNumber())
                        .orElseThrow(() -> new Exception(ErrorMessage.BUILDING_NOT_FOUND.getMessage())))));
    }

    public List<WashingMachineDto> findByBuildingValidateDto(Integer Building_Id) {
        return washingMachineRepository.findWashingMachinesByBuilding_Id(Building_Id)
                .stream().filter(washingMachine -> washingMachine.getStatus().equals(Boolean.TRUE))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private WashingMachineDto toDTO(WashingMachine washingMachine) {
        return new WashingMachineDto(
                washingMachine.getId(), washingMachine.getStatus(),
                washingMachine.getFloorBumber(), washingMachine.getBuilding().getId());
    }

}
