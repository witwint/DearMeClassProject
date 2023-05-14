package classproject.dearme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//유저 등록을 위한 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreateDto {

	private String username;

	private String email;

	private String password;

	private String phone;
}
