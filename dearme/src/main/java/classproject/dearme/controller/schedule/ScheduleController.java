package classproject.dearme.controller.schedule;

import classproject.dearme.dto.schedule.ScheduleInfoDto;
import classproject.dearme.dto.schedule.TodoInfoDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.schedule.ScheduleService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

	private final ScheduleService scheduleService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Response saveSchedule(@RequestBody ScheduleInfoDto scheduleInfoDto) {
		return Response.success(scheduleService.save(scheduleInfoDto));
	}

	@PostMapping("/todo")
	@ResponseStatus(HttpStatus.OK)
	public Response saveTodo(@RequestBody TodoInfoDto todoInfoDto) {
		return Response.success(scheduleService.saveTodo(todoInfoDto));
	}

	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response getScheduleInfo(@PathVariable String username) {
		return Response.success(scheduleService.getScheduleInfo(username));
	}

	@GetMapping("/todo/{scheduleid}")
	@ResponseStatus(HttpStatus.OK)
	public Response getScheduleInfo(@PathVariable Long scheduleid) {
		return Response.success(scheduleService.getTodoInfo(scheduleid));
	}

	@PatchMapping("/todo")
	@ResponseStatus(HttpStatus.OK)
	public Response updateTodoInfo(@RequestBody TodoInfoDto todoInfoDto) {
		return Response.success(scheduleService.updateTodo(todoInfoDto));
	}

	@DeleteMapping("/{scheduleid}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteSchedule(@PathVariable Long scheduleid) {
		return Response.success(scheduleService.deleteSchedule(scheduleid));
	}

	@DeleteMapping("/todo/{todoid}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteTodo(@PathVariable Long todoid) {
		return Response.success(scheduleService.deleteTodo(todoid));
	}
}
