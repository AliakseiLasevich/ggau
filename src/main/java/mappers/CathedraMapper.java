package mappers;

import dto.CathedraDto;
import entity.Cathedra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import response.CathedraRest;

@Mapper(componentModel = "spring")
public interface CathedraMapper {

    CathedraDto toDto(Cathedra cathedra);

    CathedraRest dtoToRest(CathedraDto cathedraDto);

    CathedraMapper INSTANCE = Mappers.getMapper(CathedraMapper.class);


}
