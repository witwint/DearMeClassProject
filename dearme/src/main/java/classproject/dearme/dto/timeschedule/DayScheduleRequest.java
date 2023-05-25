package classproject.dearme.dto.timeschedule;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ApiModel(value = "스케줄 Request / userName:유저이름, today:오늘메시지, tomorrow:내일메시지, date:날짜(2023-05-25 형식 지켜주세요)")
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
