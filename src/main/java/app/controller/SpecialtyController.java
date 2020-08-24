package app.controller;

import app.dto.request.SpecialtyRequest;
import app.dto.response.SpecialtyResponse;
import app.exception.ErrorMessages;
import app.exception.SpecialtyException;
import app.service.interfaces.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

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
        checkRequestModel(specialtyRequest);
        return specialtyService.createSpecialty(specialtyRequest);
    }

    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public SpecialtyResponse putSpecialty(@RequestBody SpecialtyRequest specialtyRequest, @PathVariable String publicId) {
        checkRequestModel(specialtyRequest);
      return specialtyService.updateSpecialty(specialtyRequest, publicId);

    }

    @DeleteMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecialty(@PathVariable String publicId) {
      specialtyService.deleteSpecialty(publicId);
    }

    private void checkRequestModel(@RequestBody SpecialtyRequest specialtyRequest) {
        if (specialtyRequest.getName() == null) {
            throw new SpecialtyException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
    }
}
