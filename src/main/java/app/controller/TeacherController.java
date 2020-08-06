package app.controller;

import app.exception.ErrorMessages;
import app.exception.TeacherException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import app.dto.request.TeacherRequest;
import app.dto.response.TeacherResponse;
import app.service.interfaces.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/rest/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TeacherResponse getTeacher(@PathVariable String publicId) {
        return teacherService.findById(publicId);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherResponse> findAllTeachers() {
        return teacherService.findAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponse postTeacher(@RequestBody TeacherRequest teacherRequest) {
        if (teacherRequest.getName().isEmpty() || teacherRequest.getCathedraId() == null) {
            throw new TeacherException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        return teacherService.createTeacher(teacherRequest);
    }

    @PutMapping("/{publicId}")
    public TeacherResponse putTeacher(@RequestBody TeacherRequest teacherRequest, @PathVariable String publicId) {
        if (teacherRequest.getName().isEmpty() || teacherRequest.getCathedraId() == null) {
            throw new TeacherException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        return teacherService.updateTeacher(teacherRequest, publicId);
    }

    @DeleteMapping("/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeacher(@PathVariable String publicId) {
        teacherService.deleteTeacher(publicId);
    }
}
