package app.controller;

import app.converters.LearnPlanMapper;
import app.dto.request.LearnPlanRequest;
import app.dto.response.LearnPlanResponse;
import app.entity.LearnPlan;
import app.service.interfaces.LearnPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/learn_plan")
public class LearnPlanController {

    @Autowired
    private LearnPlanService learnPlanService;

    @PostMapping
    public LearnPlanResponse createLearnPlan(@RequestBody LearnPlanRequest learnPlanRequest) {
        return learnPlanService.createLearnPlan(learnPlanRequest);
    }
}
