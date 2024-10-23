package csulb.mingle.domain.timetable.dto.request;

import csulb.mingle.domain.timetable.entity.TimeTable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAndUpdateTimeTableRequestDto {

    @NotBlank(message = "timetable name is required")
    private String name;

    public TimeTable toEntity() {
        return TimeTable.builder()
                .name(name)
                .build();
    }
}
