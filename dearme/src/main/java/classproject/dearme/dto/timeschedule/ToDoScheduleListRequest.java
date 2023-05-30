package classproject.dearme.dto.timeschedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "todo 리스트 스케줄 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ToDoScheduleListRequest {
	@ApiModelProperty(value = "todo 스케줄의 리스트 형태")
	List<ToDoScheduleRequest> toDoScheduleRequestList;

}
