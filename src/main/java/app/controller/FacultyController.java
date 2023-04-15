package app.controller;

import app.model.dto.request.FacultyRequest;
import app.model.dto.response.FacultyResponse;
import app.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequiredArgsConstructor
@RequestMapping("/rest/faculties")
public class FacultyController {

    private final FacultyService facultyService;

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
    public FacultyResponse createFaculty(@RequestBody @Valid FacultyRequest facultyRequest) {
        return facultyService.createFaculty(facultyRequest);
    }

    @PutMapping("/{publicId}")
    public FacultyResponse updateFaculty(@RequestBody @Valid FacultyRequest facultyRequest, @PathVariable String publicId) {
        facultyRequest.setPublicId(publicId);
        return facultyService.updateFaculty(facultyRequest);
    }

    @DeleteMapping("/{publicId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteFaculty(@PathVariable String publicId) {
        facultyService.deleteFaculty(publicId);
    }
}
