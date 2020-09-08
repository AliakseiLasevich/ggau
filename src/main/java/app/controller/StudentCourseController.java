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
    public StudentCourseResponse postStudentGroup(@RequestBody StudentCourseRequest studentCourseRequest) {
        return studentCourseService.createStudentCourse(studentCourseRequest);
    }

    @GetMapping
    public List<StudentCourseResponse> getAllStudentCourses(){
        return studentCourseService.getAllStudentCourses();
    }

}
