package csulb.mingle.domain.timetable.repository;

import csulb.mingle.domain.timetable.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
    
}
