package classproject.dearme.dto.user;

import classproject.dearme.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserInfoDto {

	private String username;

	private String email;

	private String phone;

	private String content;

	private String image;

	public static UserInfoDto toDto(User user) {
		return UserInfoDto.builder()
			.username(user.getUsername())
			.email(user.getEmail())
			.phone(user.getPhone())
			.content(user.getContent())
			.image(user.getImage())
			.build();
	}

}
