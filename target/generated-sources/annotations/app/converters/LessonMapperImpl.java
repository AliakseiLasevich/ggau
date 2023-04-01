package app.converters;

import app.model.mapper.LessonMapper;
import app.model.dto.request.LessonRequest;
import app.model.dto.response.CabinetResponse;
import app.model.dto.response.CathedraResponse;
import app.model.dto.response.DisciplineResponse;
import app.model.dto.response.FacultyResponse;
import app.model.dto.response.LessonResponse;
import app.model.dto.response.StudentSubgroupResponse;
import app.model.dto.response.TeacherResponse;
import app.model.entity.Cabinet;
import app.model.entity.Cathedra;
import app.model.entity.Discipline;
import app.model.entity.Faculty;
import app.model.entity.Lesson;
import app.model.entity.StudentSubgroup;
import app.model.entity.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class LessonMapperImpl implements LessonMapper {

    @Override
    public Lesson requestToEntity(LessonRequest lessonRequest) {
        if ( lessonRequest == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        return lesson;
    }

    @Override
    public LessonResponse entityToResponse(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonResponse lessonResponse = new LessonResponse();

        lessonResponse.setPublicId( lesson.getPublicId() );
        lessonResponse.setType( lesson.getType() );
        lessonResponse.setOrder( lesson.getOrder() );
        lessonResponse.setDateTime( lesson.getDateTime() );
        lessonResponse.setDiscipline( disciplineToDisciplineResponse( lesson.getDiscipline() ) );
        lessonResponse.setTeacher( teacherToTeacherResponse( lesson.getTeacher() ) );
        lessonResponse.setCabinet( cabinetToCabinetResponse( lesson.getCabinet() ) );
        lessonResponse.setStudentSubgroups( studentSubgroupListToStudentSubgroupResponseList( lesson.getStudentSubgroups() ) );

        return lessonResponse;
    }

    protected DisciplineResponse disciplineToDisciplineResponse(Discipline discipline) {
        if ( discipline == null ) {
            return null;
        }

        DisciplineResponse disciplineResponse = new DisciplineResponse();

        disciplineResponse.setName( discipline.getName() );
        disciplineResponse.setPublicId( discipline.getPublicId() );

        return disciplineResponse;
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

    protected TeacherResponse teacherToTeacherResponse(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherResponse teacherResponse = new TeacherResponse();

        teacherResponse.setPublicId( teacher.getPublicId() );
        teacherResponse.setName( teacher.getName() );
        teacherResponse.setCathedra( cathedraToCathedraResponse( teacher.getCathedra() ) );
        teacherResponse.setNote( teacher.getNote() );

        return teacherResponse;
    }

    protected CabinetResponse cabinetToCabinetResponse(Cabinet cabinet) {
        if ( cabinet == null ) {
            return null;
        }

        CabinetResponse cabinetResponse = new CabinetResponse();

        cabinetResponse.setPublicId( cabinet.getPublicId() );
        cabinetResponse.setNumber( cabinet.getNumber() );
        cabinetResponse.setType( cabinet.getType() );
        cabinetResponse.setMaxStudents( cabinet.getMaxStudents() );

        return cabinetResponse;
    }

    protected StudentSubgroupResponse studentSubgroupToStudentSubgroupResponse(StudentSubgroup studentSubgroup) {
        if ( studentSubgroup == null ) {
            return null;
        }

        StudentSubgroupResponse studentSubgroupResponse = new StudentSubgroupResponse();

        studentSubgroupResponse.setPublicId( studentSubgroup.getPublicId() );
        studentSubgroupResponse.setName( studentSubgroup.getName() );
        studentSubgroupResponse.setStudentsCount( studentSubgroup.getStudentsCount() );

        return studentSubgroupResponse;
    }

    protected List<StudentSubgroupResponse> studentSubgroupListToStudentSubgroupResponseList(List<StudentSubgroup> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentSubgroupResponse> list1 = new ArrayList<StudentSubgroupResponse>( list.size() );
        for ( StudentSubgroup studentSubgroup : list ) {
            list1.add( studentSubgroupToStudentSubgroupResponse( studentSubgroup ) );
        }

        return list1;
    }
}
