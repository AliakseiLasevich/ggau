package mappers;

import dto.StudentSubgroupDto;
import entity.StudentSubgroup;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.StudentSubgroupRequestModel;
import response.StudentSubgroupRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-16T17:54:10+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class StudentSubgroupMapperImpl implements StudentSubgroupMapper {

    @Override
    public StudentSubgroupRest dtoToRest(StudentSubgroupDto studentSubgroupDto) {
        if ( studentSubgroupDto == null ) {
            return null;
        }

        StudentSubgroupRest studentSubgroupRest = new StudentSubgroupRest();

        studentSubgroupRest.setId( studentSubgroupDto.getId() );
        studentSubgroupRest.setName( studentSubgroupDto.getName() );
        studentSubgroupRest.setActive( studentSubgroupDto.getActive() );

        return studentSubgroupRest;
    }

    @Override
    public StudentSubgroupDto entityToDto(StudentSubgroup studentSubgroup) {
        if ( studentSubgroup == null ) {
            return null;
        }

        StudentSubgroupDto studentSubgroupDto = new StudentSubgroupDto();

        studentSubgroupDto.setId( studentSubgroup.getId() );
        studentSubgroupDto.setName( studentSubgroup.getName() );
        studentSubgroupDto.setActive( studentSubgroup.isActive() );

        return studentSubgroupDto;
    }

    @Override
    public StudentSubgroup dtoToEntity(StudentSubgroupDto studentSubgroupDto) {
        if ( studentSubgroupDto == null ) {
            return null;
        }

        StudentSubgroup studentSubgroup = new StudentSubgroup();

        studentSubgroup.setId( studentSubgroupDto.getId() );
        studentSubgroup.setName( studentSubgroupDto.getName() );
        if ( studentSubgroupDto.getActive() != null ) {
            studentSubgroup.setActive( studentSubgroupDto.getActive() );
        }

        return studentSubgroup;
    }

    @Override
    public StudentSubgroupDto requestToDto(StudentSubgroupRequestModel studentSubgroupRequestModel) {
        if ( studentSubgroupRequestModel == null ) {
            return null;
        }

        StudentSubgroupDto studentSubgroupDto = new StudentSubgroupDto();

        studentSubgroupDto.setName( studentSubgroupRequestModel.getName() );
        studentSubgroupDto.setActive( studentSubgroupRequestModel.getActive() );
        studentSubgroupDto.setStudentGroupId( studentSubgroupRequestModel.getStudentGroupId() );

        return studentSubgroupDto;
    }
}
