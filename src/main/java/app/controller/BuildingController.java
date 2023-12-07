package app.controller;

import app.model.dto.request.BuildingRequest;
import app.model.dto.response.BuildingResponse;
import app.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/buildings")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

//    @PreAuthorize("""
//            hasRole('USER')
//            || hasRole('ADMIN')
//            """)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<BuildingResponse> getAll() {
        return buildingService.getAll();
    }

//    @PreAuthorize("""
//            hasRole('USER')
//            || hasRole('ADMIN')
//            """)
    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BuildingResponse getBuildingById(@PathVariable("publicId") String publicId) {
        return buildingService.getById(publicId);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public BuildingResponse postBuilding(@RequestBody @Valid BuildingRequest buildingRequest) {
        return buildingService.createBuilding(buildingRequest);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BuildingResponse putBuilding(@RequestBody @Valid BuildingRequest buildingRequest, @PathVariable String publicId) {
        return buildingService.updateBuilding(buildingRequest, publicId);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBuilding(@PathVariable String publicId) {
        buildingService.deleteBuilding(publicId);
    }


}
