package csulb.mingle.domain.timetable.entity;

import csulb.mingle.domain.timetable.dto.response.TimeTableResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_timetable")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 32)
    private String name;

    // user

    @Column
    private int priority;

    public TimeTableResponseDto toResponseDto() {
        return TimeTableResponseDto.builder()
                .id(id)
                .name(name)
                .build();
    }

}
