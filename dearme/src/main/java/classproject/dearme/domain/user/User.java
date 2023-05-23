package classproject.dearme.domain.user;


import classproject.dearme.domain.base.BaseEntity;
import classproject.dearme.domain.schedule.Schedule;
import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.uploadfile.UploadFile;
import classproject.dearme.dto.user.UserCreateDto;
import classproject.dearme.dto.user.UserInfoDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Getter
@Builder
@Setter
@Table(name = "USERS") //User예약어라 변경
public class User extends BaseEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id; // PK로 활용하기 위한 id값


	@Column(nullable = false)
	private String username; // 사용자 아이디

	@Column(nullable = false)
	private String password; // 사용자 비밀 번호

	@Column(nullable = false)
	private String email; // 사용자 이메일

	@Column(nullable = false)
	private String phone; // 사용자 번호

	private String content; // 사용자 소개

	private String image; // 사용자 사진

	@OneToOne(cascade = CascadeType.PERSIST)
	private UploadFile attachFile;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<UploadFile> imageFiles;

	@OneToMany
	private List<TimeCapsule> timeCapsules = new ArrayList<TimeCapsule>();

	@OneToMany
	private List<Schedule> schedules = new ArrayList<Schedule>();


	public static User getUser(UserCreateDto userCreateDto) {
		return User.builder()
			.username(userCreateDto.getUsername())
			.email(userCreateDto.getEmail())
			.password(userCreateDto.getPassword())
			.phone(userCreateDto.getPhone())
			.build();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
