package app.mappers;

import app.dto.CathedraDto;
import app.entity.Cathedra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import app.dto.request.CathedraRequest;
import app.dto.response.CathedraResponse;

@Mapper(componentModel = "spring")
public interface CathedraMapper {


    CathedraResponse entityToResponse(Cathedra cathedra);

    Cathedra requestToEntity(CathedraRequest cathedraRequest);


//    OLD

    //    @Mapping(source = "active", target = "active")
    CathedraDto entityToDto(Cathedra cathedra);

    //    @Mapping(source = "active", target = "active")
    CathedraResponse dtoToRest(CathedraDto cathedraDto);

    //    @Mapping(source = "active", target = "active")
    Cathedra dtoToEntity(CathedraDto cathedraDto);

    //    @Mapping(source = "active", target = "active")
    CathedraDto requestModelToDto(CathedraRequest requestModel);

    CathedraMapper INSTANCE = Mappers.getMapper(CathedraMapper.class);


}
