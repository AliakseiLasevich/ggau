package app.controller;

import app.dto.request.BuildingRequest;
import app.exception.BuildingException;
import app.exception.ErrorMessages;
import app.mappers.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.dto.response.BuildingResponse;
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
    public ResponseEntity<List<BuildingResponse>> findBuildings() {

        List<BuildingDto> buildingDtos = buildingService.findBuildings();

        List<BuildingResponse> buildingResponses = buildingDtos.stream()
                .map(BuildingMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(buildingResponses);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BuildingResponse getBuildingById(@PathVariable("id") Long id) {
        return BuildingMapper.INSTANCE.dtoToRest(buildingService.findById(id));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postBuilding(@RequestBody BuildingRequest buildingRequest) {

        if (buildingRequest.getName() == null) {
            throw new BuildingException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        BuildingDto buildingDto = BuildingMapper.INSTANCE.requestToDto(buildingRequest);
        buildingService.save(buildingDto);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void putBuilding(@RequestBody BuildingRequest buildingRequest,
                            @PathVariable Long id) {
        if (buildingRequest.getName() == null) {
            throw new BuildingException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        BuildingDto buildingDto = BuildingMapper.INSTANCE.requestToDto(buildingRequest);
        buildingDto.setId(id);
        buildingService.save(buildingDto);
    }


}
