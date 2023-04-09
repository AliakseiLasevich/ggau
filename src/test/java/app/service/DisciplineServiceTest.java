package app.service;

import app.dao.interfaces.DisciplineRepository;
import app.exception.DisciplineException;
import app.model.dto.request.DisciplineRequest;
import app.model.dto.response.DisciplineResponse;
import app.model.entity.Discipline;
import app.model.mapper.DisciplineMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DisciplineServiceTest {

    @Mock
    private DisciplineMapper disciplineMapper;

    @Mock
    private DisciplineRepository disciplineRepository;

    @InjectMocks
    private DisciplineService disciplineService;

    @Test
    void testFindEntityByPublicId() {
        Discipline discipline = Discipline.builder().id("1L").name("Math").build();
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.ofNullable(discipline));

        Discipline result = disciplineService.findEntityByPublicId("123");

        assertEquals(discipline, result);
    }

    @Test
    void testFindAll() {
        Discipline discipline1 = Discipline.builder().id("1L").name("Math").build();
        Discipline discipline2 = Discipline.builder().id("2L").name("Science").build();
        List<Discipline> disciplines = Arrays.asList(discipline1, discipline2);
        when(disciplineRepository.findAllByActiveTrue()).thenReturn(disciplines);

        DisciplineResponse disciplineResponse1 = DisciplineResponse.builder().publicId("1L").name("Math").build();
        DisciplineResponse disciplineResponse2 = DisciplineResponse.builder().publicId("2L").name("Science").build();
        List<DisciplineResponse> expectedResponses = Arrays.asList(disciplineResponse1, disciplineResponse2);
        when(disciplineMapper.entityToResponse(discipline1)).thenReturn(disciplineResponse1);
        when(disciplineMapper.entityToResponse(discipline2)).thenReturn(disciplineResponse2);

        List<DisciplineResponse> result = disciplineService.findAll();

        assertEquals(expectedResponses, result);
    }


    @Test
    void testFindEntityByPublicIdWhenDisciplineExists() {
        Discipline discipline = Discipline.builder().id("1L").name("Math").build();
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.of(discipline));

        Discipline result = disciplineService.findEntityByPublicId("123");

        assertEquals(discipline, result);
    }

    @Test
    void testFindEntityByPublicIdWhenDisciplineDoesNotExist() {
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.empty());

        assertThrows(DisciplineException.class, () -> disciplineService.findEntityByPublicId("123"));
    }


    @Test
    void testFindByPublicIdWhenDisciplineExists() {
        Discipline discipline = Discipline.builder().id("1L").publicId("123").name("Math").build();
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.of(discipline));
        when(disciplineMapper.entityToResponse(any())).thenReturn(DisciplineResponse.builder().publicId("123").name("Math").build());
        DisciplineResponse result = disciplineService.findByPublicId("123");

        DisciplineResponse expectedResponse = DisciplineResponse.builder().publicId("1L").name("Math").build();
        assertEquals(expectedResponse.getName(), result.getName());
    }

    @Test
    void testFindByPublicIdWhenDisciplineDoesNotExist() {
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.empty());

        assertThrows(DisciplineException.class, () -> disciplineService.findByPublicId("123"));
    }

    @Test
    void testCreateDiscipline() {
        DisciplineRequest disciplineRequest = DisciplineRequest.builder().name("Math").build();
        Discipline discipline = Discipline.builder().id("1L").name("Math").build();
        Discipline savedDiscipline = Discipline.builder().id("1L").name("Math").publicId("123").build();
        DisciplineResponse expectedResponse = DisciplineResponse.builder().publicId("1L").name("Math").build();

        when(disciplineMapper.requestToEntity(disciplineRequest)).thenReturn(discipline);
        when(disciplineRepository.save(discipline)).thenReturn(savedDiscipline);
        when(disciplineMapper.entityToResponse(savedDiscipline)).thenReturn(expectedResponse);

        DisciplineResponse result = disciplineService.createDiscipline(disciplineRequest);

        assertEquals(expectedResponse, result);
        assertNotNull(savedDiscipline.getPublicId());
    }

    @Test
    void testUpdateDisciplineWhenDisciplineExists() {
        Discipline discipline = Discipline.builder().id("1L").name("Math").build();
        DisciplineRequest disciplineRequest = DisciplineRequest.builder().name("Physics").build();
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.of(discipline));
        when(disciplineRepository.save(discipline)).thenReturn(discipline);
        when(disciplineMapper.entityToResponse(discipline)).thenReturn(DisciplineResponse.builder().name("Physics").build());

        DisciplineResponse result = disciplineService.updateDiscipline(disciplineRequest, "123");

        DisciplineResponse expectedResponse = DisciplineResponse.builder().publicId("1L").name("Physics").build();
        assertEquals(expectedResponse.getName(), result.getName());
    }

    @Test
    void testUpdateDisciplineWhenDisciplineDoesNotExist() {
        DisciplineRequest disciplineRequest = DisciplineRequest.builder().name("Physics").build();
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.empty());

        assertThrows(DisciplineException.class, () -> disciplineService.updateDiscipline(disciplineRequest, "123"));
    }

    @Test
    void testDeleteDisciplineWhenDisciplineExists() {
        Discipline discipline = Discipline.builder().id("1L").name("Math").build();
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.of(discipline));
        when(disciplineRepository.save(discipline)).thenReturn(discipline);

        disciplineService.deleteDiscipline("123");

        assertFalse(discipline.isActive());
    }

    @Test
    void testDeleteDisciplineWhenDisciplineDoesNotExist() {
        when(disciplineRepository.findByPublicIdAndActiveTrue("123")).thenReturn(Optional.empty());

        assertThrows(DisciplineException.class, () -> disciplineService.deleteDiscipline("123"));
    }
}