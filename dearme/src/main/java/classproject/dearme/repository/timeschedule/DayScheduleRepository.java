package classproject.dearme.repository.timeschedule;

import classproject.dearme.domain.timeschedule.DaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {

	DaySchedule findByDateAndUser_Username(String date, String userName);

}
