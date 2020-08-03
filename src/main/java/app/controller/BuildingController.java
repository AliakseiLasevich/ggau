package app.controller;

import app.exception.BuildingException;
import app.exception.ErrorMessages;
import app.mappers.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.dto.request.BuildingRequestModel;
import app.dto.response.BuildingRest;
import app.service.interfaces.BuildingService;
import app.dto.BuildingDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/buildings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BuildingRest>> findBuildings() {

        List<BuildingDto> buildingDtos = buildingService.findBuildings();

        List<BuildingRest> buildingRests = buildingDtos.stream()
                .map(BuildingMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(buildingRests);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BuildingRest getBuildingById(@PathVariable("id") Long id) {
        return BuildingMapper.INSTANCE.dtoToRest(buildingService.findById(id));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postBuilding(@RequestBody BuildingRequestModel buildingRequestModel) {

        if (buildingRequestModel.getName() == null) {
            throw new BuildingException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        BuildingDto buildingDto = BuildingMapper.INSTANCE.requestToDto(buildingRequestModel);
        buildingService.save(buildingDto);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void putBuilding(@RequestBody BuildingRequestModel buildingRequestModel,
                            @PathVariable Long id) {
        if (buildingRequestModel.getName() == null) {
            throw new BuildingException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        BuildingDto buildingDto = BuildingMapper.INSTANCE.requestToDto(buildingRequestModel);
        buildingDto.setId(id);
        buildingService.save(buildingDto);
    }


}
