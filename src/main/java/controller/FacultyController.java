package controller;

import entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.FacultyService;


import java.util.List;

@RestController
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/")
    public List<Faculty> faculties() {

        return facultyService.findAll();
    }


}
