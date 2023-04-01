package app.converters;

import app.model.mapper.StudentGroupMapper;
import app.model.dto.request.StudentGroupRequest;
import app.model.dto.response.StudentGroupResponse;
import app.model.dto.response.StudentSubgroupResponse;
import app.model.entity.StudentGroup;
import app.model.entity.StudentSubgroup;
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
public class StudentGroupMapperImpl implements StudentGroupMapper {

    @Override
    public StudentGroupResponse entityToResponse(StudentGroup studentGroup) {
        if ( studentGroup == null ) {
            return null;
        }

        StudentGroupResponse studentGroupResponse = new StudentGroupResponse();

        studentGroupResponse.setPublicId( studentGroup.getPublicId() );
        studentGroupResponse.setNumber( studentGroup.getNumber() );
        studentGroupResponse.setStudentSubgroups( studentSubgroupListToStudentSubgroupResponseList( studentGroup.getStudentSubgroups() ) );

        return studentGroupResponse;
    }

    @Override
    public StudentGroup requestToEntity(StudentGroupRequest studentGroupRequest) {
        if ( studentGroupRequest == null ) {
            return null;
        }

        StudentGroup studentGroup = new StudentGroup();

        studentGroup.setNumber( studentGroupRequest.getNumber() );

        return studentGroup;
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
