package app.service;

import app.dao.interfaces.CabinetRepository;
import app.exception.CabinetException;
import app.model.dto.request.CabinetRequest;
import app.model.dto.response.CabinetResponse;
import app.model.entity.Building;
import app.model.entity.Cabinet;
import app.model.enums.CabinetType;
import app.model.mapper.CabinetMapper;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CabinetServiceTest {

    @Mock
    private CabinetRepository cabinetRepository;

    @Mock
    private BuildingService buildingService;

    @Mock
    private CabinetMapper cabinetMapper;

    @InjectMocks
    private CabinetService cabinetService;

    private static final String CABINET_ID = "cabinet-id";
    private static final String BUILDING_ID = "building-id";

    @Test
    void testFindById() {

        Cabinet cabinet = new Cabinet();
        cabinet.setPublicId(CABINET_ID);
        cabinet.setActive(true);

        CabinetResponse cabinetResponse = CabinetResponse.builder().publicId(CABINET_ID).build();

        when(cabinetRepository.findByPublicIdAndActiveTrue(CABINET_ID)).thenReturn(Optional.of(cabinet));
        when(cabinetMapper.entityToResponse(cabinet)).thenReturn(cabinetResponse);

        CabinetResponse actualCabinetResponse = cabinetService.findById(CABINET_ID);
        assertEquals(CABINET_ID, actualCabinetResponse.getPublicId());
        verify(cabinetRepository).findByPublicIdAndActiveTrue(CABINET_ID);
        verify(cabinetMapper).entityToResponse(cabinet);
    }

    @Test
    void testFindById_cabinetNotFound() {

        when(cabinetRepository.findByPublicIdAndActiveTrue(CABINET_ID)).thenReturn(Optional.empty());

        assertThrows(CabinetException.class, () -> cabinetService.findById(CABINET_ID));
    }

    @Test
    void testFindAll() {

        Building building = new Building();
        building.setId(BUILDING_ID);
        building.setActive(true);

        Cabinet cabinet1 = new Cabinet();
        cabinet1.setNumber("101");
        cabinet1.setBuilding(building);
        cabinet1.setMaxStudents(30);
        cabinet1.setType(CabinetType.LAB);
        cabinet1.setActive(true);

        Cabinet cabinet2 = new Cabinet();
        cabinet2.setNumber("102");
        cabinet2.setBuilding(building);
        cabinet2.setMaxStudents(25);
        cabinet2.setType(CabinetType.PRACTICE);
        cabinet2.setActive(true);

        List<Cabinet> cabinets = Arrays.asList(cabinet1, cabinet2);

        CabinetResponse cabinetResponse1 = CabinetResponse.builder()
                .number("101")
                .buildingId(BUILDING_ID)
                .maxStudents(30)
                .type(CabinetType.LAB.name())
                .build();

        CabinetResponse cabinetResponse2 = CabinetResponse.builder()
                .number("102")
                .buildingId(BUILDING_ID)
                .maxStudents(25)
                .type(CabinetType.PRACTICE.name())
                .build();

        List<CabinetResponse> expectedCabinetResponses = Arrays.asList(cabinetResponse1, cabinetResponse2);

        when(cabinetRepository.findByActiveTrueAndBuildingActiveTrue()).thenReturn(cabinets);
        when(cabinetMapper.entityToResponse(cabinet1)).thenReturn(cabinetResponse1);
        when(cabinetMapper.entityToResponse(cabinet2)).thenReturn(cabinetResponse2);


        List<CabinetResponse> actualCabinetResponses = cabinetService.findAll();


        assertEquals(expectedCabinetResponses, actualCabinetResponses);
        verify(cabinetRepository).findByActiveTrueAndBuildingActiveTrue();
        verify(cabinetMapper).entityToResponse(cabinet1);
        verify(cabinetMapper).entityToResponse(cabinet2);
    }

    @Test
    void testCreateCabinet_duplicateCabinetNumber_throwsException() {
        String buildingId = "building-id-1";
        String cabinetNumber = "101";
        CabinetRequest cabinetRequest = CabinetRequest.builder()
                .number(cabinetNumber)
                .buildingId(buildingId)
                .build();
        Building building = new Building();
        building.setId(buildingId);
        Cabinet cabinet = new Cabinet();
        cabinet.setBuilding(building);
        cabinet.setNumber(cabinetNumber);
        when(buildingService.getBuildingById(buildingId)).thenReturn(building);
        when(cabinetRepository.findByNumberAndBuildingAndActiveTrue(cabinetNumber, building)).thenReturn(cabinet);

        assertThrows(CabinetException.class, () -> cabinetService.createCabinet(cabinetRequest));

    }

    @Test
    void testCreateCabinet_success() {
        String BUILDING_ID = "building_id";
        String CABINET_NUMBER = "cabinet_number";
        int MAX_STUDENTS = 50;
        CabinetType CABINET_TYPE = CabinetType.LAB;

        CabinetRequest request = CabinetRequest.builder()
                .buildingId(BUILDING_ID)
                .number(CABINET_NUMBER)
                .maxStudents(MAX_STUDENTS)
                .type(CABINET_TYPE)
                .build();

        Building building = Building.builder()
                .id(BUILDING_ID)
                .name("Building Name")
                .active(true)
                .build();

        Cabinet cabinet = Cabinet.builder()
                .id("1L")
                .number(CABINET_NUMBER)
                .maxStudents(MAX_STUDENTS)
                .type(CABINET_TYPE)
                .building(building)
                .active(true)
                .build();

        CabinetResponse expectedResponse = CabinetResponse.builder()
                .publicId(cabinet.getPublicId())
                .number(CABINET_NUMBER)
                .maxStudents(MAX_STUDENTS)
                .type(CABINET_TYPE.name())
                .buildingId(BUILDING_ID)
                .build();

        when(buildingService.getBuildingById(BUILDING_ID)).thenReturn(building);
        when(cabinetRepository.findByNumberAndBuildingAndActiveTrue(CABINET_NUMBER, building)).thenReturn(null);
        when(cabinetMapper.requestToEntity(request)).thenReturn(cabinet);
        when(cabinetRepository.save(cabinet)).thenReturn(cabinet);
        when(cabinetMapper.entityToResponse(cabinet)).thenReturn(expectedResponse);

        CabinetResponse actualResponse = cabinetService.createCabinet(request);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getPublicId(), actualResponse.getPublicId());
        assertEquals(expectedResponse.getNumber(), actualResponse.getNumber());
        assertEquals(expectedResponse.getMaxStudents(), actualResponse.getMaxStudents());
        assertEquals(expectedResponse.getType(), actualResponse.getType());
        assertEquals(expectedResponse.getBuildingId(), actualResponse.getBuildingId());

        verify(buildingService).getBuildingById(BUILDING_ID);
        verify(cabinetRepository).findByNumberAndBuildingAndActiveTrue(CABINET_NUMBER, building);
        verify(cabinetMapper).requestToEntity(request);
        verify(cabinetRepository).save(cabinet);
        verify(cabinetMapper).entityToResponse(cabinet);
    }

    @Test
    void testUpdateCabinet_successfulUpdate() {

        Building building = getBuilding();
        Cabinet cabinet = getCabinet(building);
        CabinetRequest cabinetRequest = getCabinetRequest(building);
        cabinetRequest.setBuildingId(null);

        when(cabinetRepository.findByPublicIdAndActiveTrue(cabinet.getPublicId()))
                .thenReturn(Optional.of(cabinet));
        when(cabinetRepository.save(cabinet)).thenReturn(cabinet);
        when(cabinetMapper.entityToResponse(any())).thenReturn(
                CabinetResponse.builder()
                        .number("New cabinet number")
                        .maxStudents(50)
                        .type(CabinetType.LECTURE.name())
                        .build());

        CabinetResponse response = cabinetService.updateCabinet(cabinetRequest, cabinet.getPublicId());

        verify(cabinetRepository).findByPublicIdAndActiveTrue(cabinet.getPublicId());
        verify(cabinetRepository).save(cabinet);

        assertNotNull(response);
        assertEquals(response.getNumber(), cabinetRequest.getNumber());
        assertEquals(response.getMaxStudents(), cabinetRequest.getMaxStudents());
        assertEquals(response.getType(), cabinetRequest.getType().name());
    }

    private static Building getBuilding() {
        return Building.builder()
                .publicId("building-public-id")
                .name("Building name")
                .build();
    }

    private static CabinetRequest getCabinetRequest(Building building) {
        return CabinetRequest.builder()
                .number("New cabinet number")
                .maxStudents(50)
                .type(CabinetType.LECTURE)
                .buildingId(building.getPublicId())
                .build();
    }

    private static Cabinet getCabinet(Building building) {
        return Cabinet.builder()
                .publicId("cabinet-public-id")
                .number("Cabinet number")
                .maxStudents(30)
                .type(CabinetType.LECTURE)
                .building(building)
                .active(true)
                .build();
    }

    @Test
    void testUpdateCabinet_differentBuilding_throwsException() {
        Building building = getBuilding();
        Cabinet cabinet = getCabinet(building);
        CabinetRequest cabinetRequest = getCabinetRequest(building);

        when(cabinetRepository.findByPublicIdAndActiveTrue(cabinet.getPublicId()))
                .thenReturn(Optional.of(cabinet));

        assertThrows(CabinetException.class, () -> cabinetService.updateCabinet(cabinetRequest, cabinet.getPublicId()));
    }

    @Test
    void testUpdateCabinet_cabinetNotFound() {
        Cabinet cabinet = getCabinet(getBuilding());
        CabinetRequest cabinetRequest = getCabinetRequest(getBuilding());
        when(cabinetRepository.findByPublicIdAndActiveTrue(cabinet.getPublicId()))
                .thenReturn(Optional.ofNullable(null));

        assertThrows(CabinetException.class, () -> cabinetService.updateCabinet(cabinetRequest, cabinet.getPublicId()));
    }

    @Test
    void testDeleteCabinet_cabinetFound() {
        // Arrange
        String publicId = "12345";
        Cabinet cabinet = Cabinet.builder().publicId(publicId).number("101").active(true).build();
        when(cabinetRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.of(cabinet));

        // Act
        cabinetService.deleteCabinet(publicId);

        // Assert
        assertFalse(cabinet.isActive());
        verify(cabinetRepository, times(1)).save(cabinet);
    }

    @Test
    void testDeleteCabinet_cabinetNotFound() {
        // Arrange
        String publicId = "12345";
        when(cabinetRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.ofNullable(null));

        // Act and Assert
        assertThrows(CabinetException.class, () -> cabinetService.deleteCabinet(publicId));
        verify(cabinetRepository, never()).save(any());
    }
}
