package controller;

import dto.SpecialtyDto;
import exception.ErrorMessages;
import exception.SpecialtyException;
import mappers.SpecialtyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.SpecialtyRequestModel;
import response.SpecialtyRest;
import service.interfaces.SpecialtyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/specialties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SpecialtyRest>> findSpecialities() {
        List<SpecialtyDto> specialtyDtos = specialtyService.findSpecialities();

        List<SpecialtyRest> specialtyRests = specialtyDtos.stream()
                .map(SpecialtyMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(specialtyRests);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SpecialtyRest getSpecialtyById(@PathVariable("id") Long id) {
        return SpecialtyMapper.INSTANCE.dtoToRest(specialtyService.findById(id));
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postSpecialty(@RequestBody SpecialtyRequestModel specialtyRequestModel) {

        if (specialtyRequestModel.getName() == null || specialtyRequestModel.getFacultyId() == null) {
            throw new SpecialtyException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        SpecialtyDto specialtyDto = SpecialtyMapper.INSTANCE.requestToDto(specialtyRequestModel);
        specialtyService.save(specialtyDto);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void putSpecialty(@RequestBody SpecialtyRequestModel specialtyRequestModel,
                             @PathVariable Long id) {
        if (specialtyRequestModel.getName() == null || specialtyRequestModel.getFacultyId() == null) {
            throw new SpecialtyException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        SpecialtyDto specialtyDto = SpecialtyMapper.INSTANCE.requestToDto(specialtyRequestModel);
        specialtyDto.setId(id);
        specialtyService.save(specialtyDto);
    }
}
