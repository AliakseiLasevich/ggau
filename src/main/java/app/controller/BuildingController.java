package app.controller;

import app.dto.request.BuildingRequest;
import app.dto.response.BuildingResponse;
import app.exception.BuildingException;
import app.exception.ErrorMessages;
import app.service.interfaces.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<BuildingResponse> findBuildings() {
        return buildingService.findBuildings();
    }

    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BuildingResponse getBuildingById(@PathVariable("publicId") String publicId) {
        return buildingService.findById(publicId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public BuildingResponse postBuilding(@RequestBody BuildingRequest buildingRequest) {
        if (buildingRequest.getName() == null) {
            throw new BuildingException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        return buildingService.createBuilding(buildingRequest);
    }

    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BuildingResponse putBuilding(@RequestBody BuildingRequest buildingRequest, @PathVariable String publicId) {
        if (buildingRequest.getName() == null) {
            throw new BuildingException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        return buildingService.updateBuilding(buildingRequest, publicId);
    }

    @DeleteMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void deleteBuilding(@PathVariable String publicId) {
        buildingService.deleteBuilding(publicId);
    }


}
