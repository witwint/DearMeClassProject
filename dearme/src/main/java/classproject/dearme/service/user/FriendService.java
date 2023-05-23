package classproject.dearme.service.user;


import classproject.dearme.domain.user.Friend;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.user.FriendDto;
import classproject.dearme.dto.user.FriendInfoDto;
import classproject.dearme.repository.user.FriendRepository;
import classproject.dearme.repository.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.SAXParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendService {

	private final FriendRepository friendRepository;
	private final UserRepository userRepository;

	@Transactional
	public FriendInfoDto getHisFollower(String userName) {
		List<Friend> friends = friendRepository.findByFollower_Username(userName);
		FriendInfoDto friendInfoDto = new FriendInfoDto();
		friendInfoDto.setUserName(userName);
		List<String> ops = new ArrayList<>();
		if (friends == null) {
			return friendInfoDto;
		}
		for (Friend friend : friends) {
			ops.add(friend.getFollowee().getUsername());
		}
		friendInfoDto.setOpponent(ops);
		return friendInfoDto;
	}

	@Transactional
	public FriendInfoDto getHisFollowee(String userName) {
		List<Friend> friends = friendRepository.findByFollowee_Username(userName);
		FriendInfoDto friendInfoDto = new FriendInfoDto();
		friendInfoDto.setUserName(userName);
		List<String> ops = new ArrayList<>();
		if (friends == null) {
			return friendInfoDto;
		}
		for (Friend friend : friends) {
			ops.add(friend.getFollower().getUsername());
		}
		friendInfoDto.setOpponent(ops);
		return friendInfoDto;
	}

	@Transactional
	public FriendInfoDto getFriendYet(String userName) {
		List<Friend> friendsFollower = friendRepository.findByFollower_Username(userName);
		List<User> friendsFollowersUser = new ArrayList<>();
		for (Friend friend : friendsFollower) {
			friendsFollowersUser.add(friend.getFollowee());
		}
		List<Friend> friendsFollowee = friendRepository.findByFollowee_Username(userName);
		List<Friend> result = friendsFollowee.stream()
			.filter(element -> !friendsFollowersUser.contains(element.getFollower()))
			.collect(Collectors.toList());
		FriendInfoDto friendInfoDto = new FriendInfoDto();
		friendInfoDto.setUserName(userName);
		List<String> ops = new ArrayList<>();
		if (result.isEmpty()) {
			return friendInfoDto;
		}
		for (Friend friend : result) {
			log.info("not null result {},{}", friend,friend.getFollower().getUsername());
			ops.add(friend.getFollower().getUsername());
		}
		friendInfoDto.setOpponent(ops);
		return friendInfoDto;
	}

	@Transactional
	public FriendInfoDto crateFriend(String userName, String opName) {
		User user = userRepository.findByUsername(userName);
		User opUser = userRepository.findByUsername(opName);
		if (user == null || opUser == null) {
			throw  new IllegalArgumentException("not found user");
		}
		Friend friend = Friend.getFriendList(user, opUser);
		friendRepository.save(friend);
//		user.setFollowing(friend);
//		opUser.setFollowers(friend);
		List<String> myList = new ArrayList<>();
		myList.add(opName);
		return new FriendInfoDto(userName, myList);
	}

	@Transactional
	public String deleteFriend(String userName, String opName) {
		Friend friend = friendRepository.findByFollower_UsernameAndFollowee_Username(userName, opName);
		friendRepository.delete(friend);
		return "Success " + userName + "'s friend";
	}

	@Transactional
	public String deleteAll() {
		friendRepository.deleteAll();
		return "delete All friend";
	}

}
