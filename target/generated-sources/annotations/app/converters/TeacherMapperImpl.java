package app.converters;

import app.model.mapper.TeacherMapper;
import app.model.dto.request.TeacherRequest;
import app.model.dto.response.CathedraResponse;
import app.model.dto.response.FacultyResponse;
import app.model.dto.response.TeacherResponse;
import app.model.entity.Cathedra;
import app.model.entity.Faculty;
import app.model.entity.Teacher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher requestToEntity(TeacherRequest request) {
        if ( request == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setName( request.getName() );
        teacher.setNote( request.getNote() );

        return teacher;
    }

    @Override
    public TeacherResponse entityToResponse(Teacher entity) {
        if ( entity == null ) {
            return null;
        }

        TeacherResponse teacherResponse = new TeacherResponse();

        teacherResponse.setPublicId( entity.getPublicId() );
        teacherResponse.setName( entity.getName() );
        teacherResponse.setCathedra( cathedraToCathedraResponse( entity.getCathedra() ) );
        teacherResponse.setNote( entity.getNote() );

        return teacherResponse;
    }

    protected FacultyResponse facultyToFacultyResponse(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }

        FacultyResponse facultyResponse = new FacultyResponse();

        facultyResponse.setPublicId( faculty.getPublicId() );
        facultyResponse.setName( faculty.getName() );

        return facultyResponse;
    }

    protected CathedraResponse cathedraToCathedraResponse(Cathedra cathedra) {
        if ( cathedra == null ) {
            return null;
        }

        CathedraResponse cathedraResponse = new CathedraResponse();

        cathedraResponse.setPublicId( cathedra.getPublicId() );
        cathedraResponse.setName( cathedra.getName() );
        cathedraResponse.setFaculty( facultyToFacultyResponse( cathedra.getFaculty() ) );

        return cathedraResponse;
    }
}
