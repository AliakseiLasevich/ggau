package mappers;

import dto.CathedraDto;
import entity.Cathedra;
import entity.Faculty;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import response.CathedraRest;
import response.FacultyRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-02T13:42:44+0300",
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
        cathedraDto.setFaculty( cathedra.getFaculty() );

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
        cathedraRest.setFaculty( facultyToFacultyRest( cathedraDto.getFaculty() ) );

        return cathedraRest;
    }

    protected FacultyRest facultyToFacultyRest(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }

        FacultyRest facultyRest = new FacultyRest();

        facultyRest.setId( faculty.getId() );
        facultyRest.setName( faculty.getName() );

        return facultyRest;
    }
}
