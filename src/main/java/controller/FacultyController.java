package controller;

import entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.FacultyService;


import java.util.List;

@RestController
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/faculties")
    public List<Faculty> faculties() {
        return facultyService.findAll();
    }

    @GetMapping("/faculty/{id}")
    public Faculty faculty(@PathVariable("id") Long id) {
        return facultyService.findById(id).orElse(new Faculty());
    }


}
