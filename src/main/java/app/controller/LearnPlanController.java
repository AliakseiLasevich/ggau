package app.controller;

import app.dto.request.LearnPlanRequest;
import app.dto.response.LearnPlanResponse;
import app.service.interfaces.LearnPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/rest/learn_plans")
public class LearnPlanController {

    @Autowired
    private LearnPlanService learnPlanService;

    @PostMapping
    public LearnPlanResponse createLearnPlan(@RequestBody LearnPlanRequest learnPlanRequest) {
        return learnPlanService.createLearnPlan(learnPlanRequest);
    }

    @GetMapping(value = "/{publicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public LearnPlanResponse getLearnPlan(@PathVariable String publicId) {
        return learnPlanService.getLearnPlanByPublicId(publicId);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<LearnPlanResponse> getLearnPlansByDateRange(@RequestParam(required = false) String dateStart,
                                                            @RequestParam(required = false) String dateEnd) {

        if (dateStart == null && dateEnd == null) {
            return learnPlanService.getAllLearnPlans();
        }

        return learnPlanService.getLearnPlansByDateRange(LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
    }

}
