package controller;

import dto.CathedraDto;
import dto.FacultyDto;
import entity.Cathedra;
import exception.CathedraException;
import exception.ErrorMessages;
import exception.FacultyException;
import mappers.CathedraMapper;
import mappers.FacultyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.CathedraRequestModel;
import request.FacultyRequestModel;
import response.CathedraRest;
import response.FacultyRest;
import service.interfaces.CathedraService;
import service.interfaces.FacultyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/cathedras")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CathedraController {


    @Autowired
    private CathedraService cathedraService;

    @Autowired
    private FacultyService facultyService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CathedraRest>> findCathedrasByParams(@RequestParam(required = false) Long facultyId) {

        List<CathedraDto> cathedraDtos = cathedraService.findCathedrasByParams(facultyId);

        List<CathedraRest> cathedraRests = cathedraDtos.stream()
                .map(cathedraDto -> {
                    CathedraRest cathedraRest = CathedraMapper.INSTANCE.dtoToRest(cathedraDto);
                    FacultyRest facultyRest = FacultyMapper.INSTANCE.dtoToRest(cathedraDto.getFacultyDto());
                    cathedraRest.setFaculty(facultyRest);
                    return cathedraRest;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(cathedraRests);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Cathedra getCathedra(@PathVariable("id") Long id) {
        return cathedraService.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postCathedra(@RequestBody CathedraRequestModel cathedraRequestModel) {
        if (cathedraRequestModel.getName().isEmpty()) {
            throw new CathedraException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        CathedraDto cathedraDto = CathedraMapper.INSTANCE.requestModelToDto(cathedraRequestModel);
        cathedraService.createCathedra(cathedraDto);
    }

    @PutMapping("/{id}")
    public void putCathedra(@RequestBody CathedraRequestModel cathedraRequestModel,
                            @PathVariable Long id) {

        if (cathedraRequestModel.getName().isEmpty()) {
            throw new CathedraException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        CathedraDto cathedraDto = CathedraMapper.INSTANCE.requestModelToDto(cathedraRequestModel);
        cathedraDto.setId(id);
        cathedraService.updateCathedra(cathedraDto);
    }

}
