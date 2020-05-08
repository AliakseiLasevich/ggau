package controller;

import dto.FacultyDto;
import entity.Faculty;
import exception.ErrorMessages;
import exception.FacultyException;
import mappers.FacultyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.FacultyRequestModel;
import response.FacultyRest;
import service.interfaces.FacultyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/faculties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacultyRest>> findAllFaculties(@RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<FacultyRest> returnValue = facultyService
                .findAll(page, limit).stream()
                .map(FacultyMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(returnValue);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Faculty getFaculty(@PathVariable("id") Long id) {
        return facultyService.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FacultyRest> createFaculty(@RequestBody FacultyRequestModel facultyRequestModel) {

        if (facultyRequestModel.getName().isEmpty()) {
            throw new FacultyException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        FacultyDto facultyDto = FacultyMapper.INSTANCE.requestModelToDto(facultyRequestModel);

        FacultyDto createdFaculty = facultyService.createFaculty(facultyDto);

        FacultyRest returnValue = FacultyMapper.INSTANCE.dtoToRest(createdFaculty);

        return ResponseEntity.ok()
                .body(returnValue);
    }

    @PutMapping("/{id}")
    public void updateFaculty(@RequestBody FacultyRequestModel facultyEditRequestModel,
                              @PathVariable Long id) {

        if (facultyEditRequestModel.getName().isEmpty()) {
            throw new FacultyException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        FacultyDto facultyDto = FacultyMapper.INSTANCE.requestModelToDto(facultyEditRequestModel);
        facultyDto.setId(id);

        facultyService.updateFaculty(facultyDto);


    }


}
