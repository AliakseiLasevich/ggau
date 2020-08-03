package app.controller;

import app.dto.TeacherDto;
import app.exception.ErrorMessages;
import app.exception.TeacherException;
import app.mappers.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.dto.request.TeacherRequestModel;
import app.dto.response.TeacherRest;
import app.service.interfaces.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/teachers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TeacherRest getTeacher(@PathVariable Long id) {
        return TeacherMapper.INSTANCE.dtoToRest(teacherService.findById(id));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TeacherRest>> findAllTeachers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "limit", defaultValue = "100") int limit) {


        List<TeacherRest> returnValue = teacherService
                .findAll(page, limit).stream()
                .map(TeacherMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());

        return ResponseEntity.ok()

                .body(returnValue);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TeacherRest> postTeacher(@RequestBody TeacherRequestModel teacherRequestModel) {
        if (teacherRequestModel.getName().isEmpty() || teacherRequestModel.getCathedraId() == null) {
            throw new TeacherException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        TeacherDto teacherDto = TeacherMapper.INSTANCE.requestToDto(teacherRequestModel);
        teacherService.postTeacher(teacherDto);

        TeacherRest teacherRest = TeacherMapper.INSTANCE.dtoToRest(teacherDto);
        return ResponseEntity.ok()
                .body(teacherRest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherRest> putTeacher(@RequestBody TeacherRequestModel teacherRequestModel,
                                                  @PathVariable Long id) {
        if (teacherRequestModel.getName().isEmpty() || teacherRequestModel.getCathedraId() == null) {
            throw new TeacherException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        TeacherDto teacherDto = TeacherMapper.INSTANCE.requestToDto(teacherRequestModel);
        teacherService.postTeacher(teacherDto);

        TeacherRest teacherRest = TeacherMapper.INSTANCE.dtoToRest(teacherDto);
        return ResponseEntity.ok()
                .body(teacherRest);
    }
}
