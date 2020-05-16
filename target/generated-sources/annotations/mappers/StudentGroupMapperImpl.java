package mappers;

import dto.SpecialtyDto;
import dto.StudentGroupDto;
import entity.Specialty;
import entity.StudentGroup;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.StudentGroupRequestModel;
import response.StudentGroupRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-16T17:54:10+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class StudentGroupMapperImpl implements StudentGroupMapper {

    @Override
    public StudentGroup dtoToEntity(StudentGroupDto studentGroupDto) {
        if ( studentGroupDto == null ) {
            return null;
        }

        StudentGroup studentGroup = new StudentGroup();

        studentGroup.setId( studentGroupDto.getId() );
        studentGroup.setNumber( studentGroupDto.getNumber() );
        studentGroup.setCourse( studentGroupDto.getCourse() );
        studentGroup.setSpecialty( specialtyDtoToSpecialty( studentGroupDto.getSpecialty() ) );
        if ( studentGroupDto.getActive() != null ) {
            studentGroup.setActive( studentGroupDto.getActive() );
        }

        return studentGroup;
    }

    @Override
    public StudentGroupDto entityToDto(StudentGroup studentGroup) {
        if ( studentGroup == null ) {
            return null;
        }

        StudentGroupDto studentGroupDto = new StudentGroupDto();

        studentGroupDto.setId( studentGroup.getId() );
        studentGroupDto.setNumber( studentGroup.getNumber() );
        studentGroupDto.setCourse( studentGroup.getCourse() );
        studentGroupDto.setActive( studentGroup.isActive() );
        studentGroupDto.setSpecialty( specialtyToSpecialtyDto( studentGroup.getSpecialty() ) );

        return studentGroupDto;
    }

    @Override
    public StudentGroupRest dtoToRest(StudentGroupDto studentGroupDto) {
        if ( studentGroupDto == null ) {
            return null;
        }

        StudentGroupRest studentGroupRest = new StudentGroupRest();

        studentGroupRest.setId( studentGroupDto.getId() );
        studentGroupRest.setNumber( studentGroupDto.getNumber() );
        studentGroupRest.setCourse( studentGroupDto.getCourse() );
        studentGroupRest.setActive( studentGroupDto.getActive() );

        return studentGroupRest;
    }

    @Override
    public StudentGroupDto requestToDto(StudentGroupRequestModel studentGroupRequestModel) {
        if ( studentGroupRequestModel == null ) {
            return null;
        }

        StudentGroupDto studentGroupDto = new StudentGroupDto();

        studentGroupDto.setActive( studentGroupRequestModel.getActive() );
        studentGroupDto.setNumber( studentGroupRequestModel.getNumber() );
        studentGroupDto.setCourse( studentGroupRequestModel.getCourse() );
        studentGroupDto.setSpecialtyId( studentGroupRequestModel.getSpecialtyId() );

        return studentGroupDto;
    }

    protected Specialty specialtyDtoToSpecialty(SpecialtyDto specialtyDto) {
        if ( specialtyDto == null ) {
            return null;
        }

        Specialty specialty = new Specialty();

        specialty.setId( specialtyDto.getId() );
        specialty.setName( specialtyDto.getName() );
        specialty.setFaculty( specialtyDto.getFaculty() );
        if ( specialtyDto.getActive() != null ) {
            specialty.setActive( specialtyDto.getActive() );
        }

        return specialty;
    }

    protected SpecialtyDto specialtyToSpecialtyDto(Specialty specialty) {
        if ( specialty == null ) {
            return null;
        }

        SpecialtyDto specialtyDto = new SpecialtyDto();

        specialtyDto.setId( specialty.getId() );
        specialtyDto.setName( specialty.getName() );
        specialtyDto.setActive( specialty.isActive() );
        specialtyDto.setFaculty( specialty.getFaculty() );

        return specialtyDto;
    }
}
