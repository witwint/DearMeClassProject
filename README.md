# DearMeClassProject

---
## Dependencies

 * Spring Web
 * Lombok
 * Spring Data JPA
 * H2 Database 
 * Spring Security
 * MySQL Driver

## 스웨거 참고사이트
https://oripkgo.tistory.com/102

## 스웨거 3.0변경
https://velog.io/@kijrary/Spring-Boot-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8Gradle-%EC%97%90%EC%84%9C%EC%9D%98-Swagger-3.0.0-%EC%84%A4%EC%A0%95-%EB%B0%A9%EB%B2%95

오류해결
https://goyunji.tistory.com/137

## ELB배포
프로젝트 파일 안에서 실행해야함 eb init하고 위치 주의 cmd에서 실행해야 가능 db비번 필수입력

## 배포
url : prod-dearme-api.ap-northeast-2.elasticbeanstalk.com/
스웨거 : prod-dearme-api.ap-northeast-2.elasticbeanstalk.com/swagger-ui/index.html

## 바인딩오류
@ModelAttribute 바인딩할때는 객체에 @Data 있어야 바인딩 가능한듯

## 파일업로드 s3참고
https://europani.github.io/aws/2022/03/03/004-spring-s3.html

## 로컬오류
`com.amazonaws.SdkClientException: Failed to connect to service endpoint: `
aws sdk 에러가 나는 이유는, build.gradle에, spring-cloud-starter-aws 의존성 주입시 로컬환경은, aws환경이 아니기때문에 나는 에러라고 한다.
aws환경에서 실행시 아무문제가 없

## 스웨거 오류
요청@RequestBody 설명 너무 길면 아에안뜨고 오류남 
```
@ApiModelProperty(value = "유저 이름")
	private String userName;
  
```
변수에는 따로

@PathVariable 정보입력
https://velog.io/@gillog/Swagger-UI-Annotation-%EC%84%A4%EB%AA%85