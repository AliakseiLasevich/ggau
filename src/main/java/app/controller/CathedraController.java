package app.controller;

import app.dto.CathedraDto;
import app.entity.Cathedra;
import app.exception.CathedraException;
import app.exception.ErrorMessages;
import app.mappers.CathedraMapper;
import app.mappers.FacultyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.dto.request.CathedraRequestModel;
import app.dto.response.CathedraRest;
import app.dto.response.FacultyRest;
import app.service.interfaces.CathedraService;
import app.service.interfaces.FacultyService;

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
