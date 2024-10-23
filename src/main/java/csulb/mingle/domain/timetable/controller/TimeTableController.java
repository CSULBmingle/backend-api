package csulb.mingle.domain.timetable.controller;

import csulb.mingle.domain.timetable.dto.request.CreateAndUpdateTimeTableRequestDto;
import csulb.mingle.domain.timetable.dto.request.UpdatePriorityRequestDto;
import csulb.mingle.domain.timetable.dto.response.TimeTableListResponseDto;
import csulb.mingle.domain.timetable.dto.response.TimeTableResponseDto;
import csulb.mingle.domain.timetable.service.TimeTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class TimeTableController {
    private final TimeTableService timeTableService;

    @PostMapping("/")
    public ResponseEntity<TimeTableResponseDto> createTimeTable(
            @Valid @RequestBody CreateAndUpdateTimeTableRequestDto requestDto
    ) {
        // 유저 정보 추가
        TimeTableResponseDto responseDto = timeTableService.createTimeTable(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<TimeTableListResponseDto> getTimeTables() {
        // 특정 유저의 시간표 가져오기
        TimeTableListResponseDto responseDto = timeTableService.getTimeTables();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 친구 목록에서 timeTableId 반환
    @GetMapping("/{id}")
    public ResponseEntity<TimeTableResponseDto> getTimeTable(@PathVariable("id") Integer id) {
        // 스케줄 가져오는것이 목표. 사용 X
        TimeTableResponseDto responseDto = timeTableService.getTimeTable(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeTableResponseDto> updateTimeTable(@PathVariable("id") Integer id,
                                                                @Valid @RequestBody CreateAndUpdateTimeTableRequestDto requestDto) {
        TimeTableResponseDto responseDto = timeTableService.updateTimeTable(id, requestDto.getName());
        // 권한 체크
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/priority")
    public ResponseEntity<TimeTableResponseDto> updateTimeTablePriority(@PathVariable("id") Integer id,
                                                                        @Valid @RequestBody UpdatePriorityRequestDto requestDto) {
        // 순서 조정
        TimeTableResponseDto responseDto = timeTableService.updateTimeTablePriority(id, requestDto.getPriority());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTimeTable(@PathVariable("id") Integer id) {
        // 권한 체크
        // 뒤에있는 것들 순서 한칸씩 앞으로 당기기
        timeTableService.deleteTimeTable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
