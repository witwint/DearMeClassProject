package classproject.dearme.dto.timeschedule;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "todo 스케줄 Requset")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ToDoScheduleRequest {

	private Long dayScheduleId;

	private String content;

	private boolean checkTodo;

	private String startTime;

	private String endTime;

}
