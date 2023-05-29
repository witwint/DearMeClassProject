package classproject.dearme.service.timecapsule;

import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.timeschedule.DaySchedule;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.timecapsule.TimeCapsuleRequest;
import classproject.dearme.dto.timecapsule.TimeCapsuleResponse;
import classproject.dearme.repository.schedule.ScheduleRepository;
import classproject.dearme.repository.timecapsule.TimeCapsuleRepository;
import classproject.dearme.repository.timeschedule.DayScheduleRepository;
import classproject.dearme.repository.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeCapsuleService {

	private final TimeCapsuleRepository timeCapsuleRepository;
	private final UserRepository userRepository;

	private final DayScheduleRepository dayScheduleRepository;

	@Transactional
	public TimeCapsuleResponse save(TimeCapsuleRequest timeCapsuleRequest) {

		User user = userRepository.findByUsername(timeCapsuleRequest.getUserName());
		DaySchedule daySchedule = dayScheduleRepository.findByDateAndUser_Username(
			timeCapsuleRequest.getNextDay(), timeCapsuleRequest.getUserName()).orElse(null);
		if (daySchedule == null) {
			log.info("create if null");
			dayScheduleRepository.save(new DaySchedule(timeCapsuleRequest.getContent(), null,
				timeCapsuleRequest.getNextDay(), user));
		} else {
			daySchedule.setToday(timeCapsuleRequest.getContent());
		}

		TimeCapsule timeCapsule = new TimeCapsule(user, timeCapsuleRequest.getToDay(),
			timeCapsuleRequest.getNextDay(), timeCapsuleRequest.getContent());
		timeCapsuleRepository.save(timeCapsule);
		return TimeCapsuleResponse.toDto(timeCapsule);
	}

	@Transactional
	public List<TimeCapsuleResponse> findAll(String username) {
		User user = userRepository.findByUsername(username);
		List<TimeCapsule> resultDomain = timeCapsuleRepository.findByUser(user);
		List<TimeCapsuleResponse> result = new ArrayList<>();
		for (TimeCapsule timeCapsule : resultDomain) {
			result.add(TimeCapsuleResponse.toDto(timeCapsule));
		}
		return result;

	}

	@Transactional
	public String delete(Long id) {
		TimeCapsule timeCapsule = timeCapsuleRepository.findById(id).orElse(null);
		DaySchedule daySchedule = dayScheduleRepository.findByDateAndUser_Username(
			timeCapsule.getNextDay(), timeCapsule.getUser().getUsername()).orElse(null);
		daySchedule.setToday("");
		timeCapsuleRepository.deleteById(id);
		return id + "deleteSuccess";
	}

	@Transactional
	public void deleteAll() {
		timeCapsuleRepository.deleteAll();
	}
}
