package app.controller;

import app.model.dto.request.SpecialtyRequest;
import app.model.dto.response.SpecialtyResponse;
import app.service.interfaces.SpecialtyService;
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
@RequestMapping("/rest/specialties")
@RequiredArgsConstructor
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialtyResponse> findSpecialities() {
        return specialtyService.findSpecialities();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SpecialtyResponse getSpecialtyById(@PathVariable("id") String id) {
        return specialtyService.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyResponse postSpecialty(@RequestBody SpecialtyRequest specialtyRequest) {
        return specialtyService.createSpecialty(specialtyRequest);
    }

    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public SpecialtyResponse putSpecialty(@RequestBody SpecialtyRequest specialtyRequest, @PathVariable String publicId) {
        return specialtyService.updateSpecialty(specialtyRequest, publicId);
    }

    @DeleteMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecialty(@PathVariable String publicId) {
        specialtyService.deleteSpecialty(publicId);
    }

    @GetMapping(value = "/faculties/{facultyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialtyResponse> getSpecialitiesByFaculty(@PathVariable(required = true) String facultyId) {
        return specialtyService.findSpecialitiesByFacultyId(facultyId);
    }
}
