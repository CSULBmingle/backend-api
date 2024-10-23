package csulb.mingle.domain.timetable.service;

import csulb.mingle.domain.timetable.dto.request.CreateAndUpdateTimeTableRequestDto;
import csulb.mingle.domain.timetable.dto.response.TimeTableListResponseDto;
import csulb.mingle.domain.timetable.dto.response.TimeTableResponseDto;
import csulb.mingle.domain.timetable.entity.TimeTable;
import csulb.mingle.domain.timetable.exception.TimeTableNotFoundException;
import csulb.mingle.domain.timetable.repository.TimeTableRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeTableService {
    private final TimeTableRepository timeTableRepository;

    public TimeTableResponseDto createTimeTable(CreateAndUpdateTimeTableRequestDto requestDto) {
        TimeTable timeTable = requestDto.toEntity();
        timeTableRepository.save(timeTable);
        return timeTable.toResponseDto();
    }

    public TimeTableListResponseDto getTimeTables() {
        List<TimeTable> timeTables = timeTableRepository.findAll();
        return new TimeTableListResponseDto(
                timeTables.stream()
                        .map(TimeTable::toResponseDto)
                        .toList());
    }

    public TimeTableResponseDto getTimeTable(int id) {
        TimeTable timeTable = timeTableRepository.findById(id)
                .orElseThrow(TimeTableNotFoundException::new);

        return timeTable.toResponseDto();
    }

    public TimeTableResponseDto updateTimeTable(int id, String newName) {
        TimeTable timeTable = timeTableRepository.findById(id)
                .orElseThrow(TimeTableNotFoundException::new);
        timeTable.setName(newName);
        timeTableRepository.save(timeTable);

        return timeTable.toResponseDto();
    }

    public TimeTableResponseDto updateTimeTablePriority(int id, int newPriority) {
        // 유저가 가진 timetable 목록 확인
        TimeTable timeTable = timeTableRepository.findById(id)
                .orElseThrow(TimeTableNotFoundException::new);

        timeTableRepository.updatePriorityById(id, newPriority);

        return timeTable.toResponseDto();
    }

    public void deleteTimeTable(int id) {
        timeTableRepository.deleteById(id);
    }
}
