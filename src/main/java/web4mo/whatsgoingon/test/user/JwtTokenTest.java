//package web4mo.whatsgoingon.test.user;
//
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import web4mo.whatsgoingon.domain.user.entity.Member;
//import web4mo.whatsgoingon.domain.user.repository.UserRepository;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class JwtTokenTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(this.context)
//               // .apply(springSecurity())
//                .build();
//        Member member = Member.builder()
//                .loginId("id1")
//                .password("password")
//                .build();
//        userRepository.save(member);
//    }
//
//    @Test
//    public void 로그인() throws Exception {
//        // given
//        String username = "id1";
//        String password = "password";
//
//        // when
//        mvc.perform(formLogin()
//                        .loginProcessingUrl("/login") // 실제 로그인 URL을 명시합니다.
//                        .user("username", username) // 로그인 필드 이름이 'username'이라고 가정
//                        .password("password", password)) // 비밀번호 필드 이름이 'password'라고 가정
//                .andDo(print())
//
//                // then
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//    }
//}