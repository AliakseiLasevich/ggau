package controller;

import mappers.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import response.TeacherRest;
import service.interfaces.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rest/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TeacherRest>> findAllTeachers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "limit", defaultValue = "10") int limit) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");

        List<TeacherRest> returnValue = teacherService
                .findAll(page, limit).stream()
                .map(TeacherMapper.INSTANCE::dtoToRest)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(returnValue);
    }
}
