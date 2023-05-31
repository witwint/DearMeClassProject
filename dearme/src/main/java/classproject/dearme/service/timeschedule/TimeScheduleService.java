package classproject.dearme.service.timeschedule;

import classproject.dearme.domain.timeschedule.DaySchedule;
import classproject.dearme.domain.timeschedule.ToDoSchedule;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.timeschedule.DayScheduleResponse;
import classproject.dearme.dto.timeschedule.DayScheduleRequest;
import classproject.dearme.dto.timeschedule.ToDoScheduleListRequest;
import classproject.dearme.dto.timeschedule.ToDoScheduleRequest;
import classproject.dearme.dto.timeschedule.ToDoScheduleResponse;
import classproject.dearme.repository.timeschedule.DayScheduleRepository;
import classproject.dearme.repository.timeschedule.ToDoScheduleRepository;
import classproject.dearme.repository.user.UserRepository;
import java.time.LocalDate;
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
public class TimeScheduleService {

	private final DayScheduleRepository dayScheduleRepository;
	private final ToDoScheduleRepository toDoScheduleRepository;

	private final UserRepository userRepository;

	@Transactional
	public DayScheduleResponse saveDaySchedule(DayScheduleRequest dayScheduleRequest) {
		User user = userRepository.findByUsername(dayScheduleRequest.getUserName());
		log.info("user = {}", user.getUsername());
		DaySchedule daySchedule = new DaySchedule(dayScheduleRequest.getToday(),
			dayScheduleRequest.getTomorrow(), dayScheduleRequest.getDate(), user);
		log.info("daySchedule = {}", daySchedule.getId());
		dayScheduleRepository.save(daySchedule);
		return DayScheduleResponse.toDto(daySchedule);
	}

//	@Transactional
//	public ToDoScheduleResponse saveToDoSchedule(ToDoScheduleRequest toDoScheduleRequest) {
//		DaySchedule daySchedule = dayScheduleRepository.getReferenceById(
//			toDoScheduleRequest.getDayScheduleId());
//		ToDoSchedule toDoSchedule = new ToDoSchedule(toDoScheduleRequest.getContent(),
//			toDoScheduleRequest.isCheckTodo(), toDoScheduleRequest.getStartTime(),
//			toDoScheduleRequest.getEndTime());
//		toDoScheduleRepository.save(toDoSchedule);
//		daySchedule.addToDoSchedules(toDoSchedule);
//		log.info("save todo at dat {}", daySchedule.getToDoSchedules().size());
//
//		return ToDoScheduleResponse.toDto(toDoSchedule);
//	}

	@Transactional
	public List<ToDoScheduleResponse> saveListToDoSchedule(
		ToDoScheduleListRequest toDoScheduleListRequest) {
		DaySchedule daySchedule = dayScheduleRepository.findByDateAndUser_Username(
			toDoScheduleListRequest.getDate(),
			toDoScheduleListRequest.getUserName()).orElse(null);
		User user = userRepository.findByUsername(toDoScheduleListRequest.getUserName());
		if (daySchedule == null) {
			daySchedule = new DaySchedule(null, null, toDoScheduleListRequest.getDate(), user);
			dayScheduleRepository.save(daySchedule);
		} else {
			daySchedule.clearToDoSchedule();
		}
		List<ToDoScheduleResponse> toDoScheduleResponses = new ArrayList<>();
		for (ToDoScheduleRequest toDoScheduleRequest : toDoScheduleListRequest.getToDoScheduleRequestList()) {
			ToDoSchedule toDoSchedule = new ToDoSchedule(toDoScheduleRequest.getContent(),
				toDoScheduleRequest.isCheckTodo(), toDoScheduleRequest.getStartTime(),
				toDoScheduleRequest.getEndTime());
			toDoScheduleRepository.save(toDoSchedule);
			daySchedule.addToDoSchedules(toDoSchedule);
			log.info("save todo at dat {}", daySchedule.getToDoSchedules().size());
			toDoScheduleResponses.add(ToDoScheduleResponse.toDto(toDoSchedule));
		}

		return toDoScheduleResponses;
	}

	@Transactional
	public List<DayScheduleResponse> getWeekSchedule(String userName, int year, int month, int day) {
		LocalDate baseDate = LocalDate.of(year, month, day);
		List<DayScheduleResponse> dayScheduleResponses = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			String result = baseDate.plusDays(i).toString();
			dayScheduleResponses.add(DayScheduleResponse.toDto(
				dayScheduleRepository.findByDateAndUser_Username(result, userName).orElse(null)));
		}
		return dayScheduleResponses;
	}

	@Transactional
	public String deleteDaySchedule(Long idDaySchedule) {
		dayScheduleRepository.deleteById(idDaySchedule);
		return "Success delete DaySchedule id = " + idDaySchedule;
	}

	@Transactional
	public String deleteToDoSchedule(Long idToDoSchedule) {
		DaySchedule doSchedule = dayScheduleRepository.findByToDoSchedules_Id(
			idToDoSchedule);
	ToDoSchedule toDoSchedule = toDoScheduleRepository.getReferenceById(
			idToDoSchedule);
		doSchedule.deleteToDoSchedule(toDoSchedule);
		toDoScheduleRepository.deleteById(idToDoSchedule);
		return "Success delete DaySchedule id = " + idToDoSchedule;
	}

	@Transactional
	public String deleteAll() {
		toDoScheduleRepository.deleteAll();
		dayScheduleRepository.deleteAll();
		return "Success delete all timeSchedule";
	}

	@Transactional
	public DayScheduleResponse upDateDaySchedule(Long id, DayScheduleRequest dayScheduleRequest) {
		DaySchedule daySchedule = dayScheduleRepository.getReferenceById(id);
		daySchedule.update(dayScheduleRequest.getToday(), dayScheduleRequest.getTomorrow(),
			dayScheduleRequest.getDate());
		return DayScheduleResponse.toDto(daySchedule);
	}

	@Transactional
	public ToDoScheduleResponse upDateToDoSchedule(Long id, ToDoScheduleRequest toDoScheduleRequest) {
		ToDoSchedule toDoSchedule = toDoScheduleRepository.getReferenceById(id);
		toDoSchedule.update(toDoScheduleRequest.getContent(), toDoScheduleRequest.isCheckTodo(),
			toDoScheduleRequest.getStartTime(), toDoScheduleRequest.getEndTime());
		return ToDoScheduleResponse.toDto(toDoSchedule);
	}

	@Transactional
	public DayScheduleResponse searchDayByUserNameAndDate(String userName, int year, int month,
		int day) {
		String searchDay = LocalDate.of(year, month, day).toString();
		DaySchedule daySchedule = dayScheduleRepository.findByUser_UsernameAndDate(userName,
			searchDay);
		return DayScheduleResponse.toDto(daySchedule);

	}


}
