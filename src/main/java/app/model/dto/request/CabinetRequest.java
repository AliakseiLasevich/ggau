package app.model.dto.request;

import app.model.enums.CabinetType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CabinetRequest {

    @NotEmpty
    private String number;

    @Positive
    private int maxStudents;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private CabinetType type;

    @NotEmpty
    private String buildingId;
}
