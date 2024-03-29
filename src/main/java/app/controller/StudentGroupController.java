package app.controller;

import app.model.dto.request.StudentGroupRequest;
import app.model.dto.response.StudentGroupResponse;
import app.service.StudentGroupService;
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
@RequestMapping("/rest/studentGroups")
@RequiredArgsConstructor
public class StudentGroupController {
    private final StudentGroupService studentGroupService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentGroupResponse> getStudentGroups() {
        return studentGroupService.findAll();
    }

    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentGroupResponse getStudentGroupById(@PathVariable("publicId") String publicId) {
        return studentGroupService.findById(publicId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public StudentGroupResponse postStudentGroup(@RequestBody StudentGroupRequest studentGroupRequest) {
        return studentGroupService.createStudentGroup(studentGroupRequest);
    }

    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentGroupResponse putStudentGroup(@RequestBody StudentGroupRequest studentGroupRequest, @PathVariable("publicId") String publicId) {
        return studentGroupService.updateStudentGroup(studentGroupRequest, publicId);
    }

    @DeleteMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentGroup(@PathVariable String publicId) {
        studentGroupService.deleteStudentGroup(publicId);
    }

    @GetMapping(value = "/courses/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<StudentGroupResponse> getStudentGroupsByCourseId(@PathVariable("publicId") String publicId) {
        return studentGroupService.findAllByCourseId(publicId);
    }
}
