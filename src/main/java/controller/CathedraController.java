package controller;

import dto.CathedraDto;
import entity.Cathedra;
import mappers.CathedraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.CathedraRest;
import service.interfaces.CathedraService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/cathedras")
public class CathedraController {


    @Autowired
    private CathedraService cathedraService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CathedraRest>> findAllFaculties(@RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "limit", defaultValue = "10") int limit) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");

        List<CathedraDto> cathedraDtos = cathedraService.findAll(page, limit);

        List<CathedraRest> cathedraRests = cathedraDtos.stream()
                .map(CathedraMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());


        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(cathedraRests);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Cathedra getFaculty(@PathVariable("id") Long id) {
        return cathedraService.findById(id);
    }


//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    public FacultyRest createFaculty(@RequestBody FacultyRequestModel facultyRequestModel) {
//        if (facultyRequestModel.getName().isEmpty()) {
//            throw new FacultyException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
//        }
//
//        ModelMapper modelMapper = new ModelMapper();
//        FacultyDto facultyDto = modelMapper.map(facultyRequestModel, FacultyDto.class);
//
//        FacultyDto createdFaculty = facultyService.createFaculty(facultyDto);
//        FacultyRest returnValue = modelMapper.map(createdFaculty, FacultyRest.class);
//
//        return returnValue;
//    }
//
//    @PutMapping("/{id}")
//    public void updateFaculty() {
//    }

}
