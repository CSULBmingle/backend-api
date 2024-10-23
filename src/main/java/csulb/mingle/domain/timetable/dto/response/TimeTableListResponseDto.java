package csulb.mingle.domain.timetable.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeTableListResponseDto {
    private List<TimeTableResponseDto> timeTables;
}
