package classproject.dearme.controller.timeschedule;

import classproject.dearme.dto.timeschedule.DayScheduleRequest;
import classproject.dearme.dto.timeschedule.ToDoScheduleRequest;
import classproject.dearme.response.Response;
import classproject.dearme.service.timeschedule.TimeScheduleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "timeschedule")
@RestController
@RequiredArgsConstructor
@RequestMapping("/timeschedule")
public class TimeScheduleController {

	private final TimeScheduleService timeScheduleService;

	@PostMapping("/day")
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveDaySchedule(@RequestBody DayScheduleRequest dayScheduleRequest) {
		return Response.success(timeScheduleService.saveDaySchedule(dayScheduleRequest));
	}

	@PostMapping("/todo")
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveToDoSchedule(@RequestBody ToDoScheduleRequest toDoScheduleRequest) {
		return Response.success(timeScheduleService.saveToDoSchedule(toDoScheduleRequest));
	}

	@GetMapping("/{userName}/{year}/{month}/{day}")
	@ResponseStatus(HttpStatus.OK)
	public Response getWeekSchedule(@PathVariable String userName, @PathVariable int year,
		@PathVariable int month, @PathVariable int day) {
		return Response.success(timeScheduleService.getWeekSchedule(userName, year, month, day));
	}

	@DeleteMapping("/day/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteDaySchedule(@PathVariable Long id) {
		return Response.success(timeScheduleService.deleteDaySchedule(id));
	}

	@DeleteMapping("/todo/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteToDoSchedule(@PathVariable Long id) {
		return Response.success(timeScheduleService.deleteToDoSchedule(id));
	}

	@DeleteMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteAll() {
		return Response.success(timeScheduleService.deleteAll());
	}

	@PatchMapping("/day/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response upDateDaySchedule(@PathVariable Long id,
		@RequestBody DayScheduleRequest dayScheduleRequest) {
		return Response.success(timeScheduleService.upDateDaySchedule(id, dayScheduleRequest));
	}

	@PatchMapping("/todo/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response upDateToDoSchedule(@PathVariable Long id,
		@RequestBody ToDoScheduleRequest toDoScheduleRequest) {
		return Response.success(timeScheduleService.upDateToDoSchedule(id, toDoScheduleRequest));
	}
}
