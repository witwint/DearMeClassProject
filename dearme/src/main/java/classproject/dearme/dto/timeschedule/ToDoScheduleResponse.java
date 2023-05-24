package classproject.dearme.dto.timeschedule;

import classproject.dearme.domain.timeschedule.ToDoSchedule;
import classproject.dearme.domain.user.Friend;
import classproject.dearme.dto.user.FriendDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "todo 스케줄 Response")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ToDoScheduleResponse {

	private Long id;

	private String content;

	private boolean checkTodo;

	private String startTime;

	private String endTime;

	public static ToDoScheduleResponse toDto(ToDoSchedule toDoSchedule) {
		return ToDoScheduleResponse.builder()
			.id(toDoSchedule.getId())
			.content(toDoSchedule.getContent())
			.checkTodo(toDoSchedule.isCheckTodo())
			.startTime(toDoSchedule.getStartTime())
			.endTime(toDoSchedule.getEndTime())
			.build();
	}

}
