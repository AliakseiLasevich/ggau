package app.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonRequest {

    @NotBlank
    private String cabinetId;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String lessonType;

    @NotBlank
    private String disciplineId;

    @Positive
    private int orderNumber;

    @NotBlank
    private String teacherId;

    @NotEmpty
    private List<String> studentSubgroupIds;

    private String note;
}
