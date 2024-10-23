package csulb.mingle.domain.timetable.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeTableResponseDto {
    private int id;
    private String name;
}
