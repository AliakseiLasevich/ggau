package app.controller;

import app.dto.StudentGroupDto;
import app.exception.ErrorMessages;
import app.exception.StudentGroupException;
import app.mappers.StudentGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.dto.request.StudentGroupRequestModel;
import app.dto.response.StudentGroupRest;
import app.service.interfaces.StudentGroupService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/studentGroups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentGroupController {

    @Autowired
    StudentGroupService studentGroupService;

    @GetMapping
    public ResponseEntity<List<StudentGroupRest>> getStudentGroups() {
        List<StudentGroupDto> studentGroupDtos = studentGroupService.findAllWithSpecialtyAndSubgroups();

        List<StudentGroupRest> studentGroupRests = studentGroupDtos.stream()
                .map(studentGroupDto -> StudentGroupMapper.INSTANCE.dtoToRest(studentGroupDto)).collect(Collectors.toList());
        return ResponseEntity
                .ok(studentGroupRests);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentGroupRest getStudentGroupById(@PathVariable("id") Long id) {
        return StudentGroupMapper.INSTANCE.dtoToRest(studentGroupService.findById(id));
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postStudentGroup(@RequestBody StudentGroupRequestModel studentGroupRequestModel) {

        if (studentGroupRequestModel.getNumber() == 0 || studentGroupRequestModel.getCourse() == 0) {
            throw new StudentGroupException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        StudentGroupDto studentGroupDto = StudentGroupMapper.INSTANCE.requestToDto(studentGroupRequestModel);
        studentGroupService.save(studentGroupDto);
    }


    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void putStudentGroup(@RequestBody StudentGroupRequestModel studentGroupRequestModel,
                             @PathVariable Long id) {
        if (studentGroupRequestModel.getNumber() == 0 || studentGroupRequestModel.getCourse() == 0) {
            throw new StudentGroupException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        StudentGroupDto studentGroupDto = StudentGroupMapper.INSTANCE.requestToDto(studentGroupRequestModel);
        studentGroupDto.setId(id);
        studentGroupService.save(studentGroupDto);
    }
}