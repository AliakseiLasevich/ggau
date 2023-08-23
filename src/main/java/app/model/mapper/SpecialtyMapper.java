package app.model.mapper;

import app.model.dto.request.SpecialtyRequest;
import app.model.dto.response.SpecialtyResponse;
import app.model.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", uses = Specialty.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecialtyMapper extends AbstractMapper<Specialty, SpecialtyRequest, SpecialtyResponse> {

    @Override
    @Mapping(source = "faculty.publicId", target = "facultyId")
    SpecialtyResponse entityToResponse(Specialty specialty);

}
