package app.controller;

import app.model.dto.request.StudentSubgroupRequest;
import app.model.dto.response.StudentSubgroupResponse;
import app.service.StudentSubgroupService;
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
@RequestMapping("/rest/studentSubgroups")
@RequiredArgsConstructor
public class StudentSubGroupController {
    private final StudentSubgroupService studentSubgroupService;

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

    @DeleteMapping(value = "/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentSubgroup(@PathVariable String publicId) {
        studentSubgroupService.deleteStudentSubgroup(publicId);
    }

    @GetMapping(value = "/groups/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<StudentSubgroupResponse> getStudentSubgroupBGroupId(@PathVariable("publicId") String publicId) {
        return studentSubgroupService.findByGroupId(publicId);
    }
}
