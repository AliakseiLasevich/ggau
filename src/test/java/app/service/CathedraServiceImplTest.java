package app.service;

import app.dao.interfaces.CathedraRepository;
import app.exception.CathedraException;
import app.model.dto.request.CathedraRequest;
import app.model.dto.response.CathedraResponse;
import app.model.dto.response.FacultyResponse;
import app.model.entity.Cathedra;
import app.model.entity.Faculty;
import app.model.mapper.CathedraMapper;
import app.service.interfaces.FacultyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CathedraServiceImplTest {


    @Mock
    private CathedraRepository cathedraRepository;

    @Mock
    private FacultyService facultyService;

    @Mock
    private CathedraMapper cathedraMapper;

    @InjectMocks
    private CathedraServiceImpl cathedraService;

    private static final String PUBLIC_ID = "12345";

    @Test
    void testFindByPublicId_Success() {
        Cathedra cathedra = createCathedra();
        when(cathedraRepository.findByPublicIdAndActiveTrue(PUBLIC_ID)).thenReturn(Optional.of(cathedra));

        Cathedra result = cathedraService.findByPublicId(PUBLIC_ID);

        verify(cathedraRepository, times(1)).findByPublicIdAndActiveTrue(PUBLIC_ID);
        assertEquals(cathedra, result);
    }

    @Test
    void testFindByPublicId_NotFound() {
        when(cathedraRepository.findByPublicIdAndActiveTrue(PUBLIC_ID)).thenReturn(Optional.empty());
        assertThrows(CathedraException.class, () -> cathedraService.findByPublicId(PUBLIC_ID));
    }

    @Test
    void testGetAll() {
        Cathedra cathedra = createCathedra();
        List<Cathedra> cathedraList = new ArrayList<>();
        cathedraList.add(cathedra);
        when(cathedraRepository.findAllByActiveTrue()).thenReturn(cathedraList);
        CathedraResponse cathedraResponse = createCathedraResponse();
        when(cathedraMapper.entityToResponse(cathedra)).thenReturn(cathedraResponse);

        // when
        List<CathedraResponse> result = cathedraService.getAll();

        // then
        verify(cathedraRepository, times(1)).findAllByActiveTrue();
        verify(cathedraMapper, times(1)).entityToResponse(cathedra);
        assertEquals(1, result.size());
        assertEquals(cathedraResponse, result.get(0));
    }

    @Test
    void testCreateCathedra_Success() {
        // given
        CathedraRequest cathedraRequest = createCathedraRequest();
        Faculty faculty = createFaculty();
        Cathedra cathedra = createCathedra();
        when(cathedraRepository.findByNameAndActiveTrue(cathedraRequest.getName())).thenReturn(null);
        when(cathedraMapper.requestToEntity(cathedraRequest)).thenReturn(cathedra);
        when(facultyService.findEntityByPublicId("1")).thenReturn(faculty);
        when(cathedraRepository.save(cathedra)).thenReturn(cathedra);

        // when
        Cathedra result = cathedraService.createCathedra(cathedraRequest, "1");

        // then
        verify(cathedraRepository, times(1)).findByNameAndActiveTrue(cathedraRequest.getName());
        verify(cathedraMapper, times(1)).requestToEntity(cathedraRequest);
        verify(facultyService, times(1)).findEntityByPublicId("1");
        verify(cathedraRepository, times(1)).save(cathedra);
        assertEquals(cathedra, result);
    }


    @Test
    void testCreateCathedra_throwsExceptionIfCathedraExists() {
        CathedraRequest cathedraRequest = createCathedraRequest();
        Cathedra cathedra = createCathedra();
        when(cathedraRepository.findByNameAndActiveTrue(cathedraRequest.getName())).thenReturn(cathedra);

        assertThrows(CathedraException.class, () -> cathedraService.createCathedra(cathedraRequest, "1"));
    }

    public Faculty createFaculty() {
        return Faculty.builder()
                .name("Test Faculty")
                .publicId("faculty-id-123")
                .build();
    }

    public CathedraRequest createCathedraRequest() {
        return CathedraRequest.builder()
                .name("Test Cathedra")
                .facultyId("faculty-id-123")
                .build();
    }

    public CathedraResponse createCathedraResponse() {
        return CathedraResponse.builder()
                .name("Test Cathedra")
                .publicId("cathedra-id-123")
                .faculty(FacultyResponse.builder()
                        .name("Test Faculty")
                        .publicId("faculty-id-123")
                        .build())
                .build();
    }

    public Cathedra createCathedra() {
        return Cathedra.builder()
                .name("Test Cathedra")
                .publicId("cathedra-id-123")
                .faculty(Faculty.builder()
                        .name("Test Faculty")
                        .publicId("faculty-id-123")
                        .build())
                .build();
    }

    @Test
    void updateCathedra_shouldUpdateCathedraNameAndFaculty() {
        String publicId = "cathedra_public_id";
        Cathedra cathedra = Cathedra.builder()
                .id(1L)
                .publicId(publicId)
                .name("Old Cathedra Name")
                .active(true)
                .faculty(Faculty.builder().publicId("faculty_public_id").build())
                .build();
        CathedraRequest cathedraRequest = CathedraRequest.builder()
                .name("New Cathedra Name")
                .facultyId("new_faculty_public_id")
                .build();

        Faculty faculty = Faculty.builder()
                .id(2L)
                .publicId("new_faculty_public_id")
                .name("New Faculty Name")
                .active(true)
                .build();

        when(cathedraRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.of(cathedra));
        when(facultyService.findEntityByPublicId(cathedraRequest.getFacultyId())).thenReturn(faculty);
        when(cathedraRepository.save(cathedra)).thenReturn(cathedra);

        Cathedra updatedCathedra = cathedraService.updateCathedra(cathedraRequest, publicId);


        assertEquals(cathedraRequest.getName(), updatedCathedra.getName());
        assertEquals(faculty, updatedCathedra.getFaculty());
    }

    @Test
    void updateCathedra_shouldThrowCathedraException_whenCathedraNotFound() {
        String publicId = "invalid_cathedra_public_id";
        CathedraRequest cathedraRequest = CathedraRequest.builder()
                .name("New Cathedra Name")
                .facultyId("new_faculty_public_id")
                .build();

        when(cathedraRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.empty());
        assertThrows(CathedraException.class, () -> cathedraService.updateCathedra(cathedraRequest, publicId));
    }

    @Test
    void testDeleteCathedra() {
        // Arrange
        String publicId = "cathedra1";
        Cathedra cathedra = Cathedra.builder().publicId(publicId).name("Test Cathedra").active(true).build();
        when(cathedraRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.of(cathedra));

        // Act
        cathedraService.deleteCathedra(publicId);

        // Assert
        verify(cathedraRepository, times(1)).save(cathedra);
        assertFalse(cathedra.isActive());
    }

    @Test
    void testDeleteCathedraCathedraNotFound() {
        // Arrange
        String publicId = "cathedra1";
        when(cathedraRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.empty());

        // Act and assert
        assertThrows(CathedraException.class, () -> cathedraService.deleteCathedra(publicId));
        verify(cathedraRepository, never()).save(any(Cathedra.class));
    }


}