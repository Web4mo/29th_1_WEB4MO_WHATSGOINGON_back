//package web4mo.whatsgoingon.test.user;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.testng.annotations.Test;
//import web4mo.whatsgoingon.domain.user.entity.Member;
//import web4mo.whatsgoingon.domain.user.repository.UserRepository;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        Member user = Member.builder()
//                .loginId("id1")
//                .password("1234")
//                .name("username")
//                .build();
//
//        userRepository.save(user);
//    }
//
//    @Test
//    @WithCustomMockUser
//    public void IfUserExistsThenGetUserInfoReturnsSuccess() throws Exception {
//        mockMvc.perform(get("/auth/logIn")
//                        .header("X-AUTH-TOKEN", "aaaaaaa"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}