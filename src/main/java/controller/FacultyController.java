package controller;

import dto.FacultyDto;
import entity.Faculty;
import exception.ErrorMessages;
import exception.FacultyException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.FacultyRequestModel;
import response.FacultyRest;
import service.interfaces.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/rest/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Faculty>> findAllFaculties(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "limit", defaultValue = "10") int limit) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(facultyService.findAll(page, limit));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Faculty getFaculty(@PathVariable("id") Long id) {
        return facultyService.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public FacultyRest createFaculty(@RequestBody FacultyRequestModel facultyRequestModel) {
        if (facultyRequestModel.getName().isEmpty()) {
            throw new FacultyException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        ModelMapper modelMapper = new ModelMapper();
        FacultyDto facultyDto = modelMapper.map(facultyRequestModel, FacultyDto.class);

        FacultyDto createdFaculty = facultyService.createFaculty(facultyDto);
        FacultyRest returnValue = modelMapper.map(createdFaculty, FacultyRest.class);

        return returnValue;
    }

    @PutMapping("/{id}")
    public void updateFaculty() {
    }


}
