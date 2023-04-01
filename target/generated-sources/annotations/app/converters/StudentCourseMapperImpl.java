package app.converters;

import app.model.mapper.StudentCourseMapper;
import app.model.dto.response.FacultyResponse;
import app.model.dto.response.SpecialtyResponse;
import app.model.dto.response.StudentCourseResponse;
import app.model.entity.Faculty;
import app.model.entity.Specialty;
import app.model.entity.StudentCourse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class StudentCourseMapperImpl implements StudentCourseMapper {

    @Override
    public StudentCourseResponse entityToResponse(StudentCourse studentCourse) {
        if ( studentCourse == null ) {
            return null;
        }

        StudentCourseResponse studentCourseResponse = new StudentCourseResponse();

        studentCourseResponse.setPublicId( studentCourse.getPublicId() );
        studentCourseResponse.setCourseNumber( studentCourse.getCourseNumber() );
        studentCourseResponse.setSpecialty( specialtyToSpecialtyResponse( studentCourse.getSpecialty() ) );

        return studentCourseResponse;
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

    protected SpecialtyResponse specialtyToSpecialtyResponse(Specialty specialty) {
        if ( specialty == null ) {
            return null;
        }

        SpecialtyResponse specialtyResponse = new SpecialtyResponse();

        specialtyResponse.setPublicId( specialty.getPublicId() );
        specialtyResponse.setName( specialty.getName() );
        specialtyResponse.setCode( specialty.getCode() );
        specialtyResponse.setFaculty( facultyToFacultyResponse( specialty.getFaculty() ) );

        return specialtyResponse;
    }
}
