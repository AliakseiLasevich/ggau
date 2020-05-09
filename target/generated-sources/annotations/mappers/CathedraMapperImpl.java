package mappers;

import dto.CathedraDto;
import entity.Cathedra;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.CathedraRequestModel;
import response.CathedraRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-09T15:28:48+0300",
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

        cathedraDto.setId( cathedra.getId() );
        cathedraDto.setName( cathedra.getName() );
        cathedraDto.setActive( cathedra.getActive() );

        return cathedraDto;
    }

    @Override
    public CathedraRest dtoToRest(CathedraDto cathedraDto) {
        if ( cathedraDto == null ) {
            return null;
        }

        CathedraRest cathedraRest = new CathedraRest();

        cathedraRest.setId( cathedraDto.getId() );
        cathedraRest.setName( cathedraDto.getName() );
        cathedraRest.setActive( cathedraDto.getActive() );

        return cathedraRest;
    }

    @Override
    public CathedraDto requestModelToDto(CathedraRequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        CathedraDto cathedraDto = new CathedraDto();

        cathedraDto.setId( requestModel.getId() );
        cathedraDto.setName( requestModel.getName() );
        cathedraDto.setActive( requestModel.getActive() );

        return cathedraDto;
    }
}
