package mappers;

import dto.CathedraDto;
import entity.Cathedra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import request.CathedraRequestModel;
import response.CathedraRest;

@Mapper(componentModel = "spring")
public interface CathedraMapper {

    CathedraDto entityToDto(Cathedra cathedra);

    CathedraRest dtoToRest(CathedraDto cathedraDto);

    Cathedra dtoToEntity(CathedraDto cathedraDto);

    CathedraDto requestModelToDto(CathedraRequestModel requestModel);

    CathedraMapper INSTANCE = Mappers.getMapper(CathedraMapper.class);


}
