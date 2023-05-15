package classproject.dearme.dto.timecapsule;

import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TimeCapsuleInfoDto {

	private Long id;

	private String userName;

	private String toDay;

	private String nextDay;

	private String content;

	public static TimeCapsuleInfoDto toDto(TimeCapsule timeCapsule) {
		return TimeCapsuleInfoDto.builder()
			.id(timeCapsule.getId())
			.userName(timeCapsule.getUser().getUsername())
			.toDay(timeCapsule.getToDay())
			.nextDay(timeCapsule.getNextDay())
			.content(timeCapsule.getContent())
			.build();
	}


}
