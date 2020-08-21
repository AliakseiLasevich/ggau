package app.controller;

import app.dto.request.CabinetsRequest;
import app.dto.response.CabinetResponse;
import app.exception.CabinetException;
import app.exception.ErrorMessages;
import app.service.interfaces.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/cabinets")
public class CabinetController {

    @Autowired
    CabinetService cabinetService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CabinetResponse> findAll() {
        return cabinetService.findAll();
    }

    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CabinetResponse findCabinet(@PathVariable("publicId") String publicId) {
        return cabinetService.findById(publicId);
    }

    @GetMapping(value = "/buildings/{buildingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CabinetResponse> findByBuilding(@PathVariable String buildingId) {
        return cabinetService.findByBuilding(buildingId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CabinetResponse postCabinet(@RequestBody CabinetsRequest cabinetsRequest) {
        checkRequestModelFields(cabinetsRequest);
        return cabinetService.createCabinet(cabinetsRequest);
    }


    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public CabinetResponse putCabinet(@RequestBody CabinetsRequest cabinetsRequest, @PathVariable String publicId) {
        checkRequestModelFields(cabinetsRequest);
        return cabinetService.updateCabinet(cabinetsRequest, publicId);
    }

    @DeleteMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCabinet(@PathVariable String publicId) {
        cabinetService.deleteCabinet(publicId);
    }


    private void checkRequestModelFields(@RequestBody CabinetsRequest cabinetsRequest) {
        if (cabinetsRequest.getNumber() == null || cabinetsRequest.getMaxStudents() == 0) {
            throw new CabinetException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
    }

}
