package classproject.dearme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //시큐리티 기본로그인 제거
public class DearmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DearmeApplication.class, args);
	}

}
