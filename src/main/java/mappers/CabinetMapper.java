package mappers;

import dto.CabinetDto;
import entity.Cabinet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import request.CabinetsRequestModel;
import response.CabinetRest;

@Mapper(componentModel = "spring")
public interface CabinetMapper{

    @Mapping(source = "active", target = "active")
    CabinetDto entityToDto(Cabinet cabinet);

    @InheritInverseConfiguration
    Cabinet dtoToEntity(CabinetDto cabinetDto);

    @Mapping(source = "active", target = "active")
    CabinetRest dtoToRest(CabinetDto cabinetDto);

    @Mapping(source = "active", target = "active")
    CabinetDto requestToDto(CabinetsRequestModel requestModel);

    CabinetMapper INSTANCE = Mappers.getMapper(CabinetMapper.class);


}
