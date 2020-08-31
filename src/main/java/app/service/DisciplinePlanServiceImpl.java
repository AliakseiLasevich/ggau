package app.service;

import app.converters.DisciplinePlanMapper;
import app.dao.interfaces.DisciplinePlanRepository;
import app.dto.request.DisciplinePlanRequest;
import app.dto.response.DisciplinePlanResponse;
import app.entity.DisciplinePlan;
import app.service.interfaces.DisciplinePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DisciplinePlanServiceImpl implements DisciplinePlanService {

    @Autowired
    private DisciplinePlanRepository disciplinePlanRepository;

    @Override
    public List<DisciplinePlanResponse> createDisciplinePlans(List<DisciplinePlanRequest> disciplinePlanRequest) {
        List<DisciplinePlan> disciplinePlans = disciplinePlanRequest.stream()
                .map(DisciplinePlanMapper.INSTANCE::requestToEntity)
                .peek(disciplinePlan -> disciplinePlan.setPublicId(UUID.randomUUID().toString()))
                                .collect(Collectors.toList());

        disciplinePlans.forEach(disciplinePlan -> disciplinePlanRepository.save(disciplinePlan));

        return disciplinePlans.stream()
                .map(DisciplinePlanMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }
}
