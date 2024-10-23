package csulb.mingle.domain.timetable.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePriorityRequestDto {
    @NotNull(message = "순서는 필수값입니다.")
    private Integer priority;
}
