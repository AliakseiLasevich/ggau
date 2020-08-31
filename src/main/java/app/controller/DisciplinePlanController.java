package app.controller;

import app.dto.request.DisciplinePlanRequest;
import app.dto.response.DisciplinePlanResponse;
import app.service.interfaces.DisciplinePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/disciplines_plan")
public class DisciplinePlanController {

    @Autowired
    private DisciplinePlanService disciplinePlanService;

    @PostMapping
    public List<DisciplinePlanResponse> createLearnPlan(@RequestBody List<DisciplinePlanRequest> disciplinePlanRequest) {
        return disciplinePlanService.createDisciplinePlans(disciplinePlanRequest);
    }
}
