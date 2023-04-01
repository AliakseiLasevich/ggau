package app.converters;

import app.model.mapper.StudentSubgroupMapper;
import app.model.dto.request.StudentSubgroupRequest;
import app.model.dto.response.StudentSubgroupResponse;
import app.model.entity.StudentSubgroup;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class StudentSubgroupMapperImpl implements StudentSubgroupMapper {

    @Override
    public StudentSubgroupResponse entityToResponse(StudentSubgroup studentSubgroup) {
        if ( studentSubgroup == null ) {
            return null;
        }

        StudentSubgroupResponse studentSubgroupResponse = new StudentSubgroupResponse();

        studentSubgroupResponse.setPublicId( studentSubgroup.getPublicId() );
        studentSubgroupResponse.setName( studentSubgroup.getName() );
        studentSubgroupResponse.setStudentsCount( studentSubgroup.getStudentsCount() );

        return studentSubgroupResponse;
    }

    @Override
    public StudentSubgroup requestToEntity(StudentSubgroupRequest studentSubgroupRequest) {
        if ( studentSubgroupRequest == null ) {
            return null;
        }

        StudentSubgroup studentSubgroup = new StudentSubgroup();

        studentSubgroup.setName( studentSubgroupRequest.getName() );
        studentSubgroup.setStudentsCount( studentSubgroupRequest.getStudentsCount() );

        return studentSubgroup;
    }
}
