package mappers;

import dto.CabinetDto;
import entity.Cabinet;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.CabinetsRequestModel;
import response.CabinetRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-16T17:54:10+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class CabinetMapperImpl implements CabinetMapper {

    @Override
    public CabinetDto entityToDto(Cabinet cabinet) {
        if ( cabinet == null ) {
            return null;
        }

        CabinetDto cabinetDto = new CabinetDto();

        cabinetDto.setActive( cabinet.isActive() );
        cabinetDto.setId( cabinet.getId() );
        cabinetDto.setType( cabinet.getType() );
        cabinetDto.setNumber( cabinet.getNumber() );
        cabinetDto.setMaxStudents( cabinet.getMaxStudents() );

        return cabinetDto;
    }

    @Override
    public Cabinet dtoToEntity(CabinetDto cabinetDto) {
        if ( cabinetDto == null ) {
            return null;
        }

        Cabinet cabinet = new Cabinet();

        if ( cabinetDto.getActive() != null ) {
            cabinet.setActive( cabinetDto.getActive() );
        }
        cabinet.setId( cabinetDto.getId() );
        cabinet.setNumber( cabinetDto.getNumber() );
        cabinet.setMaxStudents( cabinetDto.getMaxStudents() );
        cabinet.setType( cabinetDto.getType() );

        return cabinet;
    }

    @Override
    public CabinetRest dtoToRest(CabinetDto cabinetDto) {
        if ( cabinetDto == null ) {
            return null;
        }

        CabinetRest cabinetRest = new CabinetRest();

        cabinetRest.setActive( cabinetDto.getActive() );
        cabinetRest.setId( cabinetDto.getId() );
        cabinetRest.setType( cabinetDto.getType() );
        cabinetRest.setMaxStudents( cabinetDto.getMaxStudents() );

        return cabinetRest;
    }

    @Override
    public CabinetDto requestToDto(CabinetsRequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        CabinetDto cabinetDto = new CabinetDto();

        cabinetDto.setActive( requestModel.getActive() );
        cabinetDto.setType( requestModel.getType() );
        cabinetDto.setNumber( requestModel.getNumber() );
        cabinetDto.setMaxStudents( requestModel.getMaxStudents() );
        cabinetDto.setBuildingId( requestModel.getBuildingId() );

        return cabinetDto;
    }
}
