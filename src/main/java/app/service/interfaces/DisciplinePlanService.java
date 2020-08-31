package app.service.interfaces;

import app.dto.request.DisciplinePlanRequest;

import app.dto.response.DisciplinePlanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DisciplinePlanService {

    List<DisciplinePlanResponse> createDisciplinePlans(List<DisciplinePlanRequest> disciplinePlanRequest);
}
