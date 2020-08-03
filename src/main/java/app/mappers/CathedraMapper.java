package app.mappers;

import app.dto.CathedraDto;
import app.entity.Cathedra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import app.dto.request.CathedraRequestModel;
import app.dto.response.CathedraRest;

@Mapper(componentModel = "spring")
public interface CathedraMapper {

    @Mapping(source = "active", target = "active")
    CathedraDto entityToDto(Cathedra cathedra);

    @Mapping(source = "active", target = "active")
    CathedraRest dtoToRest(CathedraDto cathedraDto);

    @Mapping(source = "active", target = "active")
    Cathedra dtoToEntity(CathedraDto cathedraDto);

    @Mapping(source = "active", target = "active")
    CathedraDto requestModelToDto(CathedraRequestModel requestModel);

    CathedraMapper INSTANCE = Mappers.getMapper(CathedraMapper.class);


}
