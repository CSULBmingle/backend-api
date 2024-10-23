package csulb.mingle.domain.timetable.repository;

import csulb.mingle.domain.timetable.entity.TimeTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {

    @Transactional
    @Modifying
    @Query(value = "WITH UpdatePriority AS ("
            + "UPDATE TimeTable SET priority = :priority WHERE id = :id "
            + "RETURNING id"
            + ") "
            + "UPDATE TimeTable SET priority = priority + 1 WHERE id != :id AND priority >= :priority",
            nativeQuery = true)
    public void updatePriorityById(int id, int priority);
}
