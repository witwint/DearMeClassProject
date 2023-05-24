package classproject.dearme.service.timeschedule;

import classproject.dearme.domain.timeschedule.DaySchedule;
import classproject.dearme.domain.timeschedule.ToDoSchedule;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.timeschedule.DayScheduleResponse;
import classproject.dearme.dto.timeschedule.DayScheduleRequest;
import classproject.dearme.dto.timeschedule.ToDoScheduleRequest;
import classproject.dearme.dto.timeschedule.ToDoScheduleResponse;
import classproject.dearme.repository.timeschedule.DayScheduleRepository;
import classproject.dearme.repository.timeschedule.ToDoScheduleRepository;
import classproject.dearme.repository.user.UserRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
		DaySchedule daySchedule = new DaySchedule(dayScheduleRequest.getToday(),
			dayScheduleRequest.getTomorrow(), dayScheduleRequest.getDate(), user);
		dayScheduleRepository.save(daySchedule);
		return DayScheduleResponse.toDto(daySchedule);
	}

	@Transactional
	public ToDoScheduleResponse saveToDoSchedule(ToDoScheduleRequest toDoScheduleRequest) {
		DaySchedule daySchedule = dayScheduleRepository.getReferenceById(
			toDoScheduleRequest.getDayScheduleId());
		ToDoSchedule toDoSchedule = new ToDoSchedule(toDoScheduleRequest.getContent(),
			toDoScheduleRequest.isCheckTodo(), toDoScheduleRequest.getStartTime(),
			toDoScheduleRequest.getEndTime());
		toDoScheduleRepository.save(toDoSchedule);
		daySchedule.addToDoSchedules(toDoSchedule);

		return ToDoScheduleResponse.toDto(toDoSchedule);
	}

	@Transactional
	public List<DayScheduleResponse> getWeekSchedule(String userName, int year, int month, int day) {
		LocalDate baseDate = LocalDate.of(year, month, day);
		List<DayScheduleResponse> dayScheduleResponses = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			String result = baseDate.plusDays(i).toString();
			dayScheduleResponses.add(DayScheduleResponse.toDto(
				dayScheduleRepository.findByDateAndUser_Username(result, userName)));
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
		toDoScheduleRepository.deleteById(idToDoSchedule);
		return "Success delete DaySchedule id = " + idToDoSchedule;
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
		toDoSchedule.update(toDoSchedule.getContent(), toDoScheduleRequest.isCheckTodo(),
			toDoScheduleRequest.getStartTime(), toDoScheduleRequest.getEndTime());
		return ToDoScheduleResponse.toDto(toDoSchedule);
	}

//	@Transactional
//	public DayScheduleResponse saveToDo(DayScheduleRequest scheduleRequest) {
//
//		User user = userRepository.findByUsername(scheduleRequest.getUserName());
//		DaySchedule daySchedule = dayScheduleRepository.findByDateAndUser_Username(scheduleRequest.getDate(),scheduleRequest.getUserName());
//		ToDoSchedule toDoSchedule = new ToDoSchedule(scheduleRequest.getContent(),
//			scheduleRequest.isCheckTodo(), scheduleRequest.getStartTime(),
//			scheduleRequest.getEndTime());
//
//		if (daySchedule == null) {
//			DaySchedule newDaySchedule = new DaySchedule(scheduleRequest.getToday(),
//				scheduleRequest.getTomorrow(), scheduleRequest.getDate());
//			daySchedule = newDaySchedule;
//			daySchedule.setUser(user);
//		}
//
//		daySchedule.addToDoSchedules(toDoSchedule);
//
//		DayScheduleResponse dayScheduleResponse = DayScheduleResponse.toDto(daySchedule);
//		return dayScheduleResponse;
//	}
//
//	@Transactional
//	public List<DayScheduleResponse> getWeekSchedule(List<String> date, String userName) {
//		List<DayScheduleResponse> dayScheduleResponses = new ArrayList<>();
//		for (String s : date) {
//			dayScheduleResponses.add(DayScheduleResponse.toDto(dayScheduleRepository.findByDateAndUser_Username(s, userName)));
//
//		}
//		return dayScheduleResponses;
//
//	}


}
