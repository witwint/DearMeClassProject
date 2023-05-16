package classproject.dearme.controller.user;

import classproject.dearme.domain.user.User;
import classproject.dearme.dto.user.UserCreateDto;
import classproject.dearme.dto.user.UserLogin;
import classproject.dearme.dto.user.UserUpdateDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Api(tags = "유저")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	//회원가입


	@ApiOperation(
		value = "유저회원가입",
		notes = "회원가입시 사용하는 API")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveUser(@RequestBody UserCreateDto userCreateDto) {
		return Response.success(userService.saveUser(userCreateDto));
	}

	//회원정보 조회
	@ApiOperation(
		value = "유저정보",
		notes = "마이페이지에서 데이터 불러오는 API")
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)

	public Response getUserInfo(@PathVariable String username) {
		return Response.success(userService.getUserInfo(username));
	}

	//회원 정보수정 소개,사진
	@ApiOperation(
		value = "유저정보수정",
		notes = "마이페이지에서 유저정보를 수정하는 API")
	@PatchMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response updateUserInfo(@RequestBody UserUpdateDto userUpdateDto) {
		return Response.success(userService.updateUser(userUpdateDto));
	}

	//회원 로그인
	@ApiOperation(
		value = "유저 로그인",
		notes = "로그인할때 사용하는 API")
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public Response login(@RequestBody UserLogin userLogin) {
		return Response.success(userService.login(userLogin));
	}
}
