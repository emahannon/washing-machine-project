package cz.cvut.fit.si1.server.presentation.controller;

import cz.cvut.fit.si1.server.business.dto.WashingMachineDto;
import cz.cvut.fit.si1.server.business.service.WashingMachineService;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping(value = "/wm")
public class WashingMachineController {
    private final WashingMachineService washingMachineService;

    @Autowired
    public WashingMachineController(WashingMachineService washingMachineService) {
        this.washingMachineService = washingMachineService;
    }

    @GetMapping("/building/{id_building}")
    public List<WashingMachineDto> getValidatedWashingMachineByBuilding(@PathVariable Integer id_building) {
        List<WashingMachineDto> washingMachineDtoList = Collections.emptyList();
        try {
            washingMachineDtoList = washingMachineService.findByBuildingValidateDto(id_building);
        } catch (Exception e) {
            if (e.getMessage().equals(ErrorMessage.BUILDING_NOT_FOUND.getMessage()))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return washingMachineDtoList;
    }
}
