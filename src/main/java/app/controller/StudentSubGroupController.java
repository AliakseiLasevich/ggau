package app.controller;

import app.dto.StudentSubgroupDto;
import app.exception.ErrorMessages;
import app.exception.StudentSubgroupException;
import app.converters.StudentSubgroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import app.dto.request.StudentSubgroupRequestModel;
import app.dto.response.StudentSubgroupResponse;
import app.service.interfaces.StudentSubgroupService;

@RestController
@RequestMapping("/rest/studentSubgroups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentSubGroupController {

    @Autowired
    StudentSubgroupService studentSubgroupService;

    //No need in 'findAll' method

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentSubgroupResponse getStudentSubgroupById(@PathVariable("id") Long id) {
        return StudentSubgroupMapper.INSTANCE.dtoToRest(studentSubgroupService.findById(id));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postStudentSubgroup(@RequestBody StudentSubgroupRequestModel studentSubgroupRequestModel) {
        if (studentSubgroupRequestModel.getName() == null) {
            throw new StudentSubgroupException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        StudentSubgroupDto studentSubgroupDto = StudentSubgroupMapper.INSTANCE.requestToDto(studentSubgroupRequestModel);
        studentSubgroupService.save(studentSubgroupDto);
    }

    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void putStudentSubgroup(@RequestBody StudentSubgroupRequestModel studentSubgroupRequestModel,
                                   @PathVariable String publicId) {
        if (studentSubgroupRequestModel.getName() == null) {
            throw new StudentSubgroupException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        StudentSubgroupDto studentSubgroupDto = StudentSubgroupMapper.INSTANCE.requestToDto(studentSubgroupRequestModel);

        studentSubgroupService.save(studentSubgroupDto);
    }
}
