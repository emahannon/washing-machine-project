package cz.cvut.fit.si1.server.business.service.serviceimpl;

import cz.cvut.fit.si1.server.business.dto.WashingMachineCreateDto;
import cz.cvut.fit.si1.server.business.dto.WashingMachineDto;
import cz.cvut.fit.si1.server.business.service.baseservice.WashingMachineBaseService;
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
public class WashingMachineService implements
        WashingMachineBaseService<WashingMachineDto, WashingMachineCreateDto, WashingMachine, Integer> {
    private final WashingMachineRepository washingMachineRepository;
    private final BuildingRepository buildingRepository;


    /**
     * Constructor for WashingMachineService class.
     *
     * @param washingMachineRepository Instance of the WashingMachineRepository Interface
     * @param buildingRepository       Instance of the BuildingRepository Interface
     */
    @Autowired
    public WashingMachineService(WashingMachineRepository washingMachineRepository, BuildingRepository buildingRepository) {
        this.washingMachineRepository = washingMachineRepository;
        this.buildingRepository = buildingRepository;
    }

    /**
     * Method to create a new washing machine in the database.
     *
     * @param washingMachineCreateDto WashingMachineCreateDto object with the information to create a new WashingMachine
     * @return WashingMachineDto - WashingMachineDto object resulting from the creation of the new WashingMachine
     * @throws Exception Thrown if the BuldingNumber or the FloorNumber is null. Also, if the Building, which id is in the WashingMachineCreateDto object, is not found
     */
    public WashingMachineDto create(WashingMachineCreateDto washingMachineCreateDto) throws Exception {
        if (washingMachineCreateDto.getBuildingNumber() == null || washingMachineCreateDto.getFloorNumber() == null) {
            throw new Exception(ErrorMessage.NOT_NULL_VARIABLES.getMessage());
        }

        return toDto(washingMachineRepository.save(new WashingMachine(
                washingMachineCreateDto.getStatus(), washingMachineCreateDto.getFloorNumber(),
                buildingRepository.findById(washingMachineCreateDto.getBuildingNumber())
                        .orElseThrow(() -> new Exception(ErrorMessage.BUILDING_NOT_FOUND.getMessage())))));
    }

    /**
     * Method is for future functinality
     */
    @Override
    public WashingMachineDto update(Integer id, WashingMachineCreateDto createDto) throws Exception {
        //TODO
        return null;
    }

    /**
     * Method returns all washing machines in database
     *
     * @return List{@literal <WashingMachineDto>} - List{@literal <WashingMachineDto>} that has all the WashingMachineDtos in database
     */
    @Override
    public List<WashingMachineDto> findAll() {
        return washingMachineRepository.findAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds and returns a List of washing machine DTOs based on search by building ID.
     *
     * @param Building_Id Integer that represents the id of the Building
     * @return List{@literal <WashingMachineDto>} - List{@literal <WashingMachineDto>} that has all the WashingMachineDtos of a certain Building
     */
    public List<WashingMachineDto> findByBuildingValidateDto(Integer Building_Id) {
        return washingMachineRepository.findWashingMachinesByBuilding_Id(Building_Id)
                .stream().filter(washingMachine -> washingMachine.getStatus().equals(Boolean.TRUE))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds and returns washing machine based on search by washing machine ID.
     *
     * @param washingMachine_id Integer that represents the id of the Washing Machine
     * @return Optional{@literal <WashingMachine>} - Optional{@literal <WashingMachine>} which id is the parameter
     */
    public Optional<WashingMachine> findById(Integer washingMachine_id) {
        return washingMachineRepository.findById(washingMachine_id);
    }

    /**
     * Method is for future functionality
     */
    @Override
    public Optional<WashingMachineDto> findByIdAsDto(Integer id) throws Exception {
        //TODO
        return Optional.empty();
    }

    /**
     * Method is for future functionality
     */
    @Override
    public void deleteById(Integer id) throws Exception {
        //TODO
    }

    /**
     * Method is for future functionality
     */
    @Override
    public Optional<WashingMachineDto> toDto(Optional<WashingMachine> entity) {
        //TODO
        return Optional.empty();
    }

    /**
     * Creates and returns a new washign machine DTO based on a washing machine.
     *
     * @param washingMachine WashingMachine to convert to Dto
     * @return WashingMachineDto - WashingMachineDto resulting from the conversion
     */
    public WashingMachineDto toDto(WashingMachine washingMachine) {
        return new WashingMachineDto(
                washingMachine.getId(), washingMachine.getStatus(),
                washingMachine.getFloorBumber(), washingMachine.getBuilding().getId());
    }

}
