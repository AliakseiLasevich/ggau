package app.controller;

import app.dto.StudentSubgroupDto;
import app.exception.ErrorMessages;
import app.exception.StudentSubgroupException;
import app.mappers.StudentSubgroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import app.dto.request.StudentSubgroupRequestModel;
import app.dto.response.StudentSubgroupRest;
import app.service.interfaces.StudentSubgroupService;

@RestController
@RequestMapping("/rest/studentSubgroups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentSubGroupController {

    @Autowired
    StudentSubgroupService studentSubgroupService;
    
    //No need in 'findAll' method
    
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentSubgroupRest getStudentSubgroupById(@PathVariable("id") Long id) {
        return StudentSubgroupMapper.INSTANCE.dtoToRest(studentSubgroupService.findById(id));
    }
    
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postStudentSubgroup(@RequestBody StudentSubgroupRequestModel studentSubgroupRequestModel) {
        if (studentSubgroupRequestModel.getNumber() == 0 || studentSubgroupRequestModel.getName() == null) {
            throw new StudentSubgroupException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        StudentSubgroupDto studentSubgroupDto = StudentSubgroupMapper.INSTANCE.requestToDto(studentSubgroupRequestModel);
        studentSubgroupService.save(studentSubgroupDto);
    }


    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void putStudentSubgroup(@RequestBody StudentSubgroupRequestModel studentSubgroupRequestModel,
                             @PathVariable Long id) {
        if (studentSubgroupRequestModel.getNumber() == 0 || studentSubgroupRequestModel.getName() == null) {
            throw new StudentSubgroupException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        StudentSubgroupDto studentSubgroupDto = StudentSubgroupMapper.INSTANCE.requestToDto(studentSubgroupRequestModel);
        studentSubgroupDto.setId(id);
        studentSubgroupService.save(studentSubgroupDto);
    }
}
