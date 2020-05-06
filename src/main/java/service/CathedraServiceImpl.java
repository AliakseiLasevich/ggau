package service;

import dao.interfaces.CathedraRepository;
import dto.CathedraDto;
import entity.Cathedra;
import exception.CathedraException;
import exception.ErrorMessages;
import mappers.CathedraMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.interfaces.CathedraService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CathedraServiceImpl implements CathedraService {

    @Autowired
    CathedraRepository cathedraRepository;

    @Transactional
    @Override
    public List<CathedraDto> findAll(int page, int limit) {


        if (page > 0) page -= 1;
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Cathedra> cathedrasPage = cathedraRepository.findAll(pageableRequest);

        List<Cathedra> cathedraEntities = cathedrasPage.getContent();

        return cathedraEntities.stream()
                .peek(cathedra -> Hibernate.unproxy(cathedra.getFaculty()))
                .map(CathedraMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Cathedra findById(Long id) {

        return cathedraRepository
                .findById(id)
                .orElseThrow((() -> new CathedraException(ErrorMessages.NO_CATHEDRA_FOUND.getErrorMessage())));
    }
}
