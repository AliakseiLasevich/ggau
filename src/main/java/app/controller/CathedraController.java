package app.controller;

import app.model.dto.request.CathedraRequest;
import app.model.dto.response.CathedraResponse;
import app.service.CathedraService;
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
@RequestMapping("/rest/cathedras")
public class CathedraController {

    private final CathedraService cathedraService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<CathedraResponse> findAll() {
        return cathedraService.getAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void postCathedra(@RequestBody @Valid CathedraRequest cathedraRequest) {
        cathedraService.createCathedra(cathedraRequest);
    }

    @PutMapping("/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void putCathedra(@RequestBody @Valid CathedraRequest cathedraRequest, @PathVariable String publicId) {
        cathedraService.updateCathedra(cathedraRequest, publicId);
    }

    @DeleteMapping("/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCathedra(@PathVariable String publicId) {
        cathedraService.deleteCathedra(publicId);
    }


}
