package classproject.dearme.domain.user;


import classproject.dearme.domain.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Getter
@Builder
@Table(name = "USERS") //User예약어라 변경
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id; // PK로 활용하기 위한 id값

	@Column(nullable = false)
	private String password; // 사용자 비밀 번호

	@Column(nullable = false)
	private String email; // 사용자 이메일

	@Column(nullable = false)
	private String phone; // 사용자 번호

	@Column(nullable = false)
	private String content; // 사용자 소개

	@Column(nullable = false)
	private String image; // 사용자 사진
	



}
