package app.controller;

import app.dto.request.StudentCourseRequest;
import app.dto.response.StudentCourseResponse;
import app.service.interfaces.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/student_courses")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public StudentCourseResponse postStudentCourse(@RequestBody StudentCourseRequest studentCourseRequest) {
        return studentCourseService.createStudentCourse(studentCourseRequest);
    }

    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public StudentCourseResponse putStudentCourse(@PathVariable String publicId, @RequestBody StudentCourseRequest studentCourseRequest) {
        return studentCourseService.updateStudentCourse(studentCourseRequest, publicId);
    }

    @GetMapping
    public List<StudentCourseResponse> getAllStudentCourses() {
        return studentCourseService.getAllStudentCourses();
    }

    @DeleteMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentCourse(@PathVariable String publicId) {
        studentCourseService.deleteStudentCourse(publicId);
    }

    @GetMapping(value = "/faculties/{facultyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentCourseResponse>getStudentsCoursesByFaculty(@PathVariable(required = true) String facultyId) {
        return studentCourseService.getStudentsCoursesByFaculty(facultyId);
    }

    @GetMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public StudentCourseResponse getStudentCourseById(@PathVariable(required = true) String publicId) {
        return studentCourseService.getStudentCourseByPublicId(publicId);
    }

}
