package app.converters;

import app.dto.request.CathedraRequest;
import app.dto.response.CathedraResponse;
import app.entity.Cathedra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CathedraMapper.class, componentModel = "spring")
public interface CathedraMapper extends AbstractMapper<Cathedra, CathedraRequest, CathedraResponse> {

    CathedraMapper INSTANCE = Mappers.getMapper(CathedraMapper.class);
}
