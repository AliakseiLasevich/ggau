package app.controller;

import app.model.dto.request.StudentGroupRequest;
import app.model.dto.response.StudentGroupResponse;
import app.service.interfaces.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/studentGroups")
public class StudentGroupController {

    @Autowired
    StudentGroupService studentGroupService;

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
