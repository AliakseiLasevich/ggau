package mappers;

import dto.CathedraDto;
import entity.Cathedra;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.CathedraRequestModel;
import response.CathedraRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-11T16:21:45+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class CathedraMapperImpl implements CathedraMapper {

    @Override
    public CathedraDto entityToDto(Cathedra cathedra) {
        if ( cathedra == null ) {
            return null;
        }

        CathedraDto cathedraDto = new CathedraDto();

        cathedraDto.setActive( cathedra.isActive() );
        cathedraDto.setId( cathedra.getId() );
        cathedraDto.setName( cathedra.getName() );

        return cathedraDto;
    }

    @Override
    public CathedraRest dtoToRest(CathedraDto cathedraDto) {
        if ( cathedraDto == null ) {
            return null;
        }

        CathedraRest cathedraRest = new CathedraRest();

        cathedraRest.setActive( cathedraDto.isActive() );
        cathedraRest.setId( cathedraDto.getId() );
        cathedraRest.setName( cathedraDto.getName() );

        return cathedraRest;
    }

    @Override
    public Cathedra dtoToEntity(CathedraDto cathedraDto) {
        if ( cathedraDto == null ) {
            return null;
        }

        Cathedra cathedra = new Cathedra();

        cathedra.setActive( cathedraDto.isActive() );
        cathedra.setId( cathedraDto.getId() );
        cathedra.setName( cathedraDto.getName() );

        return cathedra;
    }

    @Override
    public CathedraDto requestModelToDto(CathedraRequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        CathedraDto cathedraDto = new CathedraDto();

        cathedraDto.setActive( requestModel.isActive() );
        cathedraDto.setName( requestModel.getName() );
        cathedraDto.setFacultyId( requestModel.getFacultyId() );

        return cathedraDto;
    }
}
