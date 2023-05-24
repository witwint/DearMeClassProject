package classproject.dearme.dto.timeschedule;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "스케졸 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DayScheduleRequest {

	private String userName;

	private String today;

	private String tomorrow;

	private String date;




}
