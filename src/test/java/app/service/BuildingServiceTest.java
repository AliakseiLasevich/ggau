package app.service;

import app.dao.interfaces.BuildingRepository;
import app.exception.BuildingException;
import app.model.dto.request.BuildingRequest;
import app.model.dto.response.BuildingResponse;
import app.model.entity.Building;
import app.model.mapper.BuildingMapper;
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

@ExtendWith({MockitoExtension.class})
class BuildingServiceTest {

    @Mock
    private BuildingRepository buildingRepository;

    @Mock
    private BuildingMapper buildingMapper;

    @InjectMocks
    private BuildingServiceImpl buildingService;


    @Test
    void testGetAll_returnsAllRecords_allValid() {

        List<Building> buildings = new ArrayList<>();
        Building building1 = Building.builder().name("First").build();
        Building building2 = Building.builder().name("Second").build();
        buildings.add(building1);
        buildings.add(building2);

        List<BuildingResponse> buildingResponses = new ArrayList<>();
        BuildingResponse buildingResponse1 = BuildingResponse.builder().name("First").build();
        BuildingResponse buildingResponse2 = BuildingResponse.builder().name("Second").build();
        buildingResponses.add(buildingResponse1);
        buildingResponses.add(buildingResponse2);

        when(buildingRepository.findAllByActiveTrue()).thenReturn(buildings);
        when(buildingMapper.entityToResponse(building1)).thenReturn(buildingResponse1);
        when(buildingMapper.entityToResponse(building2)).thenReturn(buildingResponse2);

        List<BuildingResponse> result = buildingService.getAll();

        assertEquals(buildingResponses, result);
    }

    @Test
    void testGetAll_returnsNonNullBuildings_WithNullBuilding() {
        // Arrange
        List<Building> buildings = new ArrayList<>();
        Building building1 = null;
        Building building2 = Building.builder().build();
        buildings.add(building1);
        buildings.add(building2);

        List<BuildingResponse> buildingResponses = new ArrayList<>();
        BuildingResponse buildingResponse2 = BuildingResponse.builder().name("Second").build();
        buildingResponses.add(buildingResponse2);

        when(buildingRepository.findAllByActiveTrue()).thenReturn(buildings);
        when(buildingMapper.entityToResponse(building2)).thenReturn(buildingResponse2);

        List<BuildingResponse> result = buildingService.getAll();

        assertEquals(buildingResponses, result);
    }

    @Test
    void testGet_returnsEmptyList_ifRepositoryReturnsEmptyResultSet() {
        List<Building> buildings = new ArrayList<>();
        when(buildingRepository.findAllByActiveTrue()).thenReturn(buildings);

        List<BuildingResponse> result = buildingService.getAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetById_worksValid_WhenBuildingExists() {
        String publicId = "building1";
        Building building = new Building();
        building.setPublicId(publicId);
        BuildingResponse buildingResponse = new BuildingResponse();
        buildingResponse.setPublicId(publicId);

        when(buildingRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.of(building));
        when(buildingMapper.entityToResponse(building)).thenReturn(buildingResponse);

        BuildingResponse result = buildingService.getById(publicId);

        assertEquals(publicId, result.getPublicId());
        verify(buildingRepository, times(1)).findByPublicIdAndActiveTrue(publicId);
        verify(buildingMapper, times(1)).entityToResponse(building);
    }

    @Test
    void testGetById_throwsException_WhenBuildingDoesNotExist() {
        String publicId = "building1";

        when(buildingRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> buildingService.getById(publicId));
        verify(buildingRepository, times(1)).findByPublicIdAndActiveTrue(publicId);
        verify(buildingMapper, never()).entityToResponse(any());
    }

    @Test
    void testCreateBuilding_savesSuccessfully_allParametersValid() {
        BuildingRequest buildingRequest = BuildingRequest.builder().name("Building 1").build();
        Building building = new Building();
        building.setName(buildingRequest.getName());

        BuildingResponse expectedResponse = new BuildingResponse();
        expectedResponse.setName(buildingRequest.getName());

        when(buildingMapper.requestToEntity(buildingRequest)).thenReturn(building);
        when(buildingMapper.entityToResponse(building)).thenReturn(expectedResponse);
        when(buildingRepository.save(building)).thenReturn(building);

        BuildingResponse actualResponse = buildingService.createBuilding(buildingRequest);


        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getName(), actualResponse.getName());

        verify(buildingRepository, times(1)).save(building);
        verify(buildingMapper, times(1)).requestToEntity(buildingRequest);
        verify(buildingMapper, times(1)).entityToResponse(building);
    }

    @Test
    void testCreateBuilding_AlreadyExists() {
        BuildingRequest buildingRequest = BuildingRequest.builder().name("Building 1").build();
        Building existingBuilding = new Building();
        existingBuilding.setName(buildingRequest.getName());

        when(buildingRepository.findByNameAndActiveTrue(buildingRequest.getName())).thenReturn(existingBuilding);

        assertThrows(BuildingException.class, () -> buildingService.createBuilding(buildingRequest));
    }


    @Test
    void testUpdateBuilding_valid() {
        // Arrange
        String publicId = "abc123";
        BuildingRequest buildingRequest = BuildingRequest.builder().name("New Building Name").build();
        Building buildingToUpdate = new Building();
        buildingToUpdate.setPublicId(publicId);
        buildingToUpdate.setName("Old Building Name");
        when(buildingRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.of(buildingToUpdate));
        when(buildingRepository.save(buildingToUpdate)).thenReturn(buildingToUpdate);
        when(buildingMapper.entityToResponse(any(Building.class))).thenReturn(BuildingResponse.builder().name("New Building Name").build());

        BuildingResponse updatedBuilding = buildingService.updateBuilding(buildingRequest, publicId);

        verify(buildingRepository, times(1)).findByPublicIdAndActiveTrue(publicId);
        verify(buildingRepository, times(1)).save(buildingToUpdate);
        assertEquals(buildingRequest.getName(), updatedBuilding.getName());
    }

    @Test
    void testUpdateBuilding_WithNonexistentBuilding() {
        String publicId = "abc123";
        BuildingRequest buildingRequest = BuildingRequest.builder().name("New Building Name").build();
        when(buildingRepository.findByPublicIdAndActiveTrue(publicId)).thenReturn(Optional.ofNullable(null));

        assertThrows(IllegalArgumentException.class, () -> buildingService.updateBuilding(buildingRequest, publicId));
        verify(buildingRepository, times(1)).findByPublicIdAndActiveTrue(publicId);
        verify(buildingRepository, never()).save(any());
    }

    @Test
    void deleteBuilding_shouldSetBuildingToInactiveAndLogInfo_whenBuildingExists() {
        String publicId = "abcd1234";
        Building building = Building.builder()
                .publicId(publicId)
                .active(true)
                .build();

        when(buildingRepository.findByPublicIdAndActiveTrue(publicId))
                .thenReturn(Optional.of(building));

        buildingService.deleteBuilding(publicId);

        assertFalse(building.getActive());
        verify(buildingRepository).save(building);
    }

    @Test
    void deleteBuilding_shouldThrowException_whenBuildingDoesNotExist() {
        String publicId = "abcd1234";
        when(buildingRepository.findByPublicIdAndActiveTrue(publicId))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            buildingService.deleteBuilding(publicId);
        });
        verify(buildingRepository, times(0)).delete(any());
    }

}
