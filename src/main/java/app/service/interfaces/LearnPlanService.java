package app.service.interfaces;

import app.dto.request.LearnPlanRequest;

import app.dto.response.LearnPlanResponse;
import org.springframework.stereotype.Service;

@Service
public interface LearnPlanService {

    LearnPlanResponse createLearnPlan(LearnPlanRequest learnPlanRequest);
}
