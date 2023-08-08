package app.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentCourseRequest {

    @NotEmpty
    private String specialtyId;

    @Positive
    private int courseNumber;
}
