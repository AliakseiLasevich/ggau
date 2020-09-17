package app.service.interfaces;

import app.dto.request.LearnPlanRequest;

import app.dto.response.LearnPlanResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface LearnPlanService {

    LearnPlanResponse createLearnPlan(LearnPlanRequest learnPlanRequest);

    LearnPlanResponse getLearnPlanByPublicId(String publicId);

    List<LearnPlanResponse> getLearnPlansByDateInclude(LocalDate date);

    List<LearnPlanResponse> getAllLearnPlans();

    void deleteLearnPlan(String publicId);
}
