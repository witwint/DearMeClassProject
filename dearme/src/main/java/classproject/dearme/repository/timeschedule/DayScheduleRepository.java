package classproject.dearme.repository.timeschedule;

import classproject.dearme.domain.timeschedule.DaySchedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {

	Optional<DaySchedule> findByDateAndUser_Username(String date, String userName);

	DaySchedule findByToDoSchedules_Id(Long id);

}
