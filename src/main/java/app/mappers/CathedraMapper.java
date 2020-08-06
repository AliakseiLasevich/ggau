package app.mappers;

import app.dto.request.CathedraRequest;
import app.dto.response.CathedraResponse;
import app.entity.Cathedra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CathedraMapper {

    CathedraResponse entityToResponse(Cathedra cathedra);

    Cathedra requestToEntity(CathedraRequest cathedraRequest);

    CathedraMapper INSTANCE = Mappers.getMapper(CathedraMapper.class);


}
