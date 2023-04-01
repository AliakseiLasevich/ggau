package app.controller;

import app.model.dto.request.DisciplineRequest;
import app.model.dto.response.DisciplineResponse;
import app.service.interfaces.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    public List<DisciplineResponse> findAllDisciplines() {
        return disciplineService.findAll();
    }

    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DisciplineResponse getDiscipline(@PathVariable("publicId") String publicId) {
        return disciplineService.findByPublicId(publicId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED)
    public DisciplineResponse createDiscipline(@RequestBody DisciplineRequest disciplineRequest) {
        return disciplineService.createDiscipline(disciplineRequest);
    }

    @PutMapping("/{publicId}")
    public DisciplineResponse updateDiscipline(@RequestBody DisciplineRequest disciplineRequest, @PathVariable String publicId) {
        return disciplineService.updateDiscipline(disciplineRequest, publicId);
    }

    @DeleteMapping("/{publicId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteFaculty(@PathVariable String publicId) {
        disciplineService.deleteDiscipline(publicId);
    }

}
