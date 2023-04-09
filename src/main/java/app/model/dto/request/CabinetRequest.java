package app.model.dto.request;

import app.model.enums.CabinetType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String number;

    @Min(1)
    private int maxStudents;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CabinetType type;

    @NotNull
    private String buildingId;
}
