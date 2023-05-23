package classproject.dearme.service.user;

import classproject.dearme.domain.user.User;
import classproject.dearme.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public User loadUserByUsername(String name) {
		User user = userRepository.findByUsername(name);
		if (user == null) {
			throw new IllegalArgumentException(name);
		}
		return user;
	}
}
