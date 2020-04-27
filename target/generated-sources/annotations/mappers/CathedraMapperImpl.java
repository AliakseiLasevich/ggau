package mappers;

import dto.CathedraDto;
import entity.Cathedra;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import response.CathedraRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-27T21:21:19+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class CathedraMapperImpl implements CathedraMapper {

    @Override
    public CathedraDto toDto(Cathedra cathedra) {
        if ( cathedra == null ) {
            return null;
        }

        CathedraDto cathedraDto = new CathedraDto();

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

        cathedraRest.setId( cathedraDto.getId() );
        cathedraRest.setName( cathedraDto.getName() );

        return cathedraRest;
    }
}
