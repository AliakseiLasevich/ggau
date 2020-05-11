package mappers;

import dto.TeacherDto;
import entity.Cathedra;
import entity.Faculty;
import entity.Teacher;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.TeacherRequestModel;
import response.CathedraRest;
import response.FacultyRest;
import response.TeacherRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-11T16:21:44+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public TeacherDto entityToDto(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setActive( teacher.isActive() );
        teacherDto.setId( teacher.getId() );
        teacherDto.setName( teacher.getName() );
        teacherDto.setCathedra( teacher.getCathedra() );

        return teacherDto;
    }

    @Override
    public TeacherRest dtoToRest(TeacherDto teacherDto) {
        if ( teacherDto == null ) {
            return null;
        }

        TeacherRest teacherRest = new TeacherRest();

        teacherRest.setActive( teacherDto.isActive() );
        teacherRest.setId( teacherDto.getId() );
        teacherRest.setName( teacherDto.getName() );
        teacherRest.setCathedra( cathedraToCathedraRest( teacherDto.getCathedra() ) );

        return teacherRest;
    }

    @Override
    public TeacherDto requestToDto(TeacherRequestModel teacherRequestModel) {
        if ( teacherRequestModel == null ) {
            return null;
        }

        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setId( teacherRequestModel.getId() );
        teacherDto.setName( teacherRequestModel.getName() );
        teacherDto.setCathedraId( teacherRequestModel.getCathedraId() );
        teacherDto.setActive( teacherRequestModel.isActive() );

        return teacherDto;
    }

    @Override
    public Teacher dtoToEntity(TeacherDto teacherDto) {
        if ( teacherDto == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setActive( teacherDto.isActive() );
        teacher.setId( teacherDto.getId() );
        teacher.setName( teacherDto.getName() );
        teacher.setCathedra( teacherDto.getCathedra() );

        return teacher;
    }

    protected FacultyRest facultyToFacultyRest(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }

        FacultyRest facultyRest = new FacultyRest();

        facultyRest.setId( faculty.getId() );
        facultyRest.setName( faculty.getName() );
        facultyRest.setActive( faculty.isActive() );

        return facultyRest;
    }

    protected CathedraRest cathedraToCathedraRest(Cathedra cathedra) {
        if ( cathedra == null ) {
            return null;
        }

        CathedraRest cathedraRest = new CathedraRest();

        cathedraRest.setId( cathedra.getId() );
        cathedraRest.setName( cathedra.getName() );
        cathedraRest.setActive( cathedra.isActive() );
        cathedraRest.setFaculty( facultyToFacultyRest( cathedra.getFaculty() ) );

        return cathedraRest;
    }
}
