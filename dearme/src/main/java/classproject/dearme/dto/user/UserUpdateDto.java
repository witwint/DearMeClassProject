package classproject.dearme.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 정보 변경 DTO username=유저이름 content=자기소개 image=사진(현재는텍스트)")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserUpdateDto {

	private String username;

	private String content;

	private String image;
}
