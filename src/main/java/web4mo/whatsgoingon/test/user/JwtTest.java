//package web4mo.whatsgoingon.test.user;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.testng.annotations.Test;
//import web4mo.whatsgoingon.domain.user.dto.SignUpRequestDto;
//import web4mo.whatsgoingon.domain.user.service.UserService;
//
//import static org.junit.Assert.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Slf4j
//public class JwtTest {
//
//
//    @Autowired
//    UserService userServiceService;
//    @Autowired
//    TestRestTemplate testRestTemplate;
//    @LocalServerPort
//    int randomServerPort;
//
//    private SignUpRequestDto signUpRequestDto;
//
//
//
//    @Test
//    public void signUpTest() {
//
//        // API 요청 설정
//        String url = "http://localhost:" + randomServerPort + "/members/sign-up";
//        ResponseEntity<SignUpRequestDto> responseEntity = testRestTemplate.postForEntity(url, SignUpRequestDto, MemberDto.class);
//
//        // 응답 검증
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        SignUpRequestDto savedMemberDto = responseEntity.getBody();
//        assertThat(savedMemberDto.getLoginId()).isEqualTo(SignUpRequestDto());
//        assertThat(savedMemberDto.getNickname()).isEqualTo(SignUpRequestDto.getNickname());
//    }
//
//}