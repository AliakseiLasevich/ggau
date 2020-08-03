package app.controller;

import app.dto.CabinetDto;
import app.dto.response.CabinetRest;
import app.exception.CabinetException;
import app.exception.ErrorMessages;
import app.mappers.CabinetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.dto.request.CabinetsRequestModel;

import app.service.interfaces.CabinetService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/cabinets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CabinetController {

    @Autowired
    CabinetService cabinetService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CabinetRest>> findCabinetsByParams(@RequestParam(required = true) Long buildingId) {



        List<CabinetDto> cabinetDtos = cabinetService.findCabinetsByParams(buildingId);

        List<CabinetRest> cabinetRests = cabinetDtos.stream()
                .map(CabinetMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(cabinetRests);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CabinetRest getCabinetById(@PathVariable("id") Long id) {
        return CabinetMapper.INSTANCE.dtoToRest(cabinetService.findById(id));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postCabinet(@RequestBody CabinetsRequestModel cabinetsRequestModel) {
        if (cabinetsRequestModel.getNumber() == 0
                || cabinetsRequestModel.getMaxStudents() == 0
                || cabinetsRequestModel.getBuildingId() == null) {
            throw new CabinetException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        CabinetDto cabinetDto = CabinetMapper.INSTANCE.requestToDto(cabinetsRequestModel);
        cabinetService.save(cabinetDto);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void putCabinet(@RequestBody CabinetsRequestModel cabinetsRequestModel,
                           @PathVariable Long id) {
        if (cabinetsRequestModel.getNumber() == 0
                || cabinetsRequestModel.getMaxStudents() == 0
                || cabinetsRequestModel.getBuildingId() == null) {
            throw new CabinetException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        CabinetDto cabinetDto = CabinetMapper.INSTANCE.requestToDto(cabinetsRequestModel);
        cabinetDto.setId(id);
        cabinetService.save(cabinetDto);
    }


}
