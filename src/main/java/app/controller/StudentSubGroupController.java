package app.controller;

import app.dto.request.StudentSubgroupRequest;
import app.dto.response.StudentSubgroupResponse;
import app.service.interfaces.StudentSubgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/studentSubgroups")
public class StudentSubGroupController {

    @Autowired
    StudentSubgroupService studentSubgroupService;

    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentSubgroupResponse getStudentSubgroupById(@PathVariable("publicId") String publicId) {
        return studentSubgroupService.findByPublicId(publicId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentSubgroupResponse postStudentSubgroup(@RequestBody StudentSubgroupRequest studentSubgroupRequest) {
        return studentSubgroupService.createStudentsSubgroup(studentSubgroupRequest);

    }

    @PutMapping(value = "/{publicId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentSubgroupResponse putStudentSubgroup(@RequestBody StudentSubgroupRequest studentSubgroupRequest,
                                                      @PathVariable String publicId) {
        return studentSubgroupService.updateStudentsSubgroup(studentSubgroupRequest, publicId);
    }
}
