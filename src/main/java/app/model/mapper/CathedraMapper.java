package app.model.mapper;

import app.model.dto.request.CathedraRequest;
import app.model.dto.response.CathedraResponse;
import app.model.entity.Cathedra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CathedraMapper.class, componentModel = "spring")
public interface CathedraMapper extends AbstractMapper<Cathedra, CathedraRequest, CathedraResponse> {

    CathedraMapper INSTANCE = Mappers.getMapper(CathedraMapper.class);
}
