package app.controller;

import app.dto.request.FacultyRequest;
import app.dto.response.FacultyResponse;
import app.exception.ErrorMessages;
import app.exception.FacultyException;
import app.service.interfaces.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    public List<FacultyResponse> findAllFaculties() {
        return facultyService.findAll();
    }

    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public FacultyResponse getFaculty(@PathVariable("publicId") String publicId) {
        return facultyService.findByPublicId(publicId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED)
    public FacultyResponse createFaculty(@RequestBody FacultyRequest facultyRequest) {
        return facultyService.createFaculty(facultyRequest);
    }

    @PutMapping("/{publicId}")
    public FacultyResponse updateFaculty(@RequestBody FacultyRequest facultyRequest, @PathVariable String publicId) {
        facultyRequest.setPublicId(publicId);
        return facultyService.updateFaculty(facultyRequest);
    }

    @DeleteMapping("/{publicId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteFaculty(@PathVariable String publicId) {
        facultyService.deleteFaculty(publicId);
    }


}
