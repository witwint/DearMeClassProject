package classproject.dearme.dto.user;

import classproject.dearme.domain.user.Friend;
import classproject.dearme.domain.user.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "친구정보 DTO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FriendDto {

	private User follower;

	private User followee;


	public static FriendDto toDto(Friend friend) {
		return FriendDto.builder()
			.follower(friend.getFollower())
			.followee(friend.getFollowee())
			.build();
	}

}
