package app.controller;

import app.dto.request.CathedraRequest;
import app.dto.response.CathedraResponse;
import app.exception.CathedraException;
import app.exception.ErrorMessages;
import app.service.interfaces.CathedraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/cathedras")
public class CathedraController {


    @Autowired
    private CathedraService cathedraService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<CathedraResponse> findAll() {
        return cathedraService.findAll();
    }

    @PostMapping(value = "/faculties/{facultyId}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void postCathedra(@RequestBody CathedraRequest cathedraRequest,
                             @PathVariable String facultyId) {
        if (cathedraRequest.getName().isEmpty()) {
            throw new CathedraException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        cathedraService.createCathedra(cathedraRequest, facultyId);
    }

    @PutMapping("/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void putCathedra(@RequestBody CathedraRequest cathedraRequest,
                            @PathVariable String publicId) {
        checkRequestValid(cathedraRequest, publicId);
        cathedraService.updateCathedra(cathedraRequest, publicId);
    }

    @DeleteMapping("/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCathedra( @PathVariable String publicId) {
        cathedraService.deleteCathedra(publicId);
    }

    private void checkRequestValid(CathedraRequest cathedraRequest, String publicId) {
        if (cathedraRequest.getName().isEmpty() || publicId == null) {
            throw new CathedraException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
    }
}
