package controller;

import dto.CathedraDto;
import dto.TeacherDto;
import exception.CathedraException;
import exception.ErrorMessages;
import exception.TeacherException;
import mappers.CathedraMapper;
import mappers.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import request.TeacherRequestModel;
import response.TeacherRest;
import service.interfaces.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rest/teachers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TeacherRest>> findAllTeachers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "limit", defaultValue = "10") int limit) {


        List<TeacherRest> returnValue = teacherService
                .findAll(page, limit).stream()
                .map(TeacherMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());

        return ResponseEntity.ok()

                .body(returnValue);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void postTeacher(@RequestBody TeacherRequestModel teacherRequestModel){
        if (teacherRequestModel.getName().isEmpty() || teacherRequestModel.getCathedraId()==null) {
            throw new TeacherException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        TeacherDto teacherDto = TeacherMapper.INSTANCE.requestToDto(teacherRequestModel);
        teacherService.postTeacher(teacherDto);
    }
}
