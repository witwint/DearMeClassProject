package classproject.dearme.dto.timeschedule;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "todo 스케줄 Requset / dayScheduleId:속할스케줄id식별자 , content:할일내용, checkTodo:체크표시, startTime:시작시작, endTime:끝시간")
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
