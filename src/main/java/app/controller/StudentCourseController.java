package app.controller;

import app.model.dto.request.StudentCourseRequest;
import app.model.dto.response.StudentCourseResponse;
import app.service.StudentCourseService;
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
@RequestMapping("/rest/student_courses")
@RequiredArgsConstructor
public class StudentCourseController {
    private final StudentCourseService studentCourseService;

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
    public List<StudentCourseResponse> getStudentsCoursesByFaculty(@PathVariable(required = true) String facultyId) {
        return studentCourseService.getStudentsCoursesByFaculty(facultyId);
    }

    @GetMapping(value = "/specialties/{specialtyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentCourseResponse> getStudentsCoursesBySpecialty(@PathVariable(required = true) String specialtyId) {
        return studentCourseService.getStudentsCoursesBySpecialty(specialtyId);
    }

    @GetMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public StudentCourseResponse getStudentCourseById(@PathVariable(required = true) String publicId) {
        return studentCourseService.getStudentCourseByPublicId(publicId);
    }

}
