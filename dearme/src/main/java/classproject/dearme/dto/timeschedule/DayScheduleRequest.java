package classproject.dearme.dto.timeschedule;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "스케줄 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Data
public class DayScheduleRequest {

	private String userName;

	private String today;

	private String tomorrow;

	private String date;




}
