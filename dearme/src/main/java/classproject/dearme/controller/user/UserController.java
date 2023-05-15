package classproject.dearme.controller.user;

import classproject.dearme.domain.user.User;
import classproject.dearme.dto.user.UserCreateDto;
import classproject.dearme.dto.user.UserLogin;
import classproject.dearme.dto.user.UserUpdateDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	//회원가입
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveUser(@RequestBody UserCreateDto userCreateDto) {
		return Response.success(userService.saveUser(userCreateDto));
	}

	//회원정보 조회
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response getUserInfo(@PathVariable String username) {
		return Response.success(userService.getUserInfo(username));
	}

	//회원 정보수정 소개,사진
	@PatchMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response updateUserInfo(@RequestBody UserUpdateDto userUpdateDto) {
		return Response.success(userService.updateUser(userUpdateDto));
	}

	//회원 로그인
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public Response login(@RequestBody UserLogin userLogin) {
		return Response.success(userService.login(userLogin));
	}
}