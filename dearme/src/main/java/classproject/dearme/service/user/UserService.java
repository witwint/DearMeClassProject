package classproject.dearme.service.user;

import static classproject.dearme.dto.user.UserInfoDto.toDto;

import classproject.dearme.domain.user.User;
import classproject.dearme.dto.user.UserCreateDto;
import classproject.dearme.dto.user.UserInfoDto;
import classproject.dearme.dto.user.UserUpdateDto;
import classproject.dearme.repository.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//회원 로직
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	//회원등록
	@Transactional
	public void saveUser(UserCreateDto userCreateDto) {
		User user = User.getUser(userCreateDto);
		userRepository.save(user);
	}

	//회원 로그인
	@Transactional
	public User login(String name, String password) {
		User findUser =  userRepository.findByUsername(name);
		if (findUser.getPassword() == password) {
			return findUser;
		} else {
			return null;
		}
	}

	//회원 마이페이지 정보 조회
	@Transactional
	public UserInfoDto getUserInfo(String name) {
		User findUser =  userRepository.findByUsername(name);
		return toDto(findUser);
	}


	//회원 정보 업데이트
	@Transactional
	public void updateUser(UserUpdateDto updateDto) {
		User findUser =  userRepository.findByUsername(updateDto.getUsername());
		findUser.setContent(updateDto.getContent());
		findUser.setImage(updateDto.getImage());
	}


}
