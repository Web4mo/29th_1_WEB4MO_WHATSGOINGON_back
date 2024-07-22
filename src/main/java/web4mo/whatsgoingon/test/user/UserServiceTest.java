//package web4mo.whatsgoingon.test.user;
//
//import jakarta.transaction.Transactional;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import web4mo.whatsgoingon.domain.user.dto.SignUpRequestDto;
//import web4mo.whatsgoingon.domain.user.entity.User;
//import web4mo.whatsgoingon.domain.user.repository.UserRepository;
//import web4mo.whatsgoingon.domain.user.service.UserService;
//
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class UserServiceTest {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @Test
//    public void 회원가입() throws Exception{
//        //given
//        String loginId="loginId";
//        String password="password";
//        String confirmPassword="password";
//        String name="name";
//        String type="type";
//
//        SignUpRequestDto userRequestDto= SignUpRequestDto.builder()
//                .loginId(loginId)
//                .password(password)
//                .confirmPassword(password)
//                .name(name)
//                .type(type)
//                .build();
//
//        //when
//        //User user=userRequestDto.toEntity();
//        Long saveId=userService.signup(userRequestDto);
//
//        //then
//        Optional<User> foundUser= userRepository.findByLoginId(loginId);
//        assertTrue(foundUser.isPresent());
//        assertEquals(loginId,foundUser.get().getLoginId());
//        assertEquals(password,foundUser.get().getPassword());
//        assertEquals(name,foundUser.get().getName());
//        assertEquals(type,foundUser.get().getType());
//        System.out.println(">>>>>>>>>> [sign_up] = "+foundUser.isPresent());
//        System.out.println(">>>>>>>>>> [loginId]="+foundUser.get().getLoginId()+"[password]="+foundUser.get().getPassword()+"[name]"+foundUser.get().getName()+"[type]"+foundUser.get().getType());
//
//    }
//
//    @Test(expected = IllegalStateException.class)
//    public void 중복_회원_예외() throws Exception{
//        //given
//        String loginId1="loginId";
//        String loginId2="loginId";
//
//        SignUpRequestDto userRequestDto1= SignUpRequestDto.builder()
//                .loginId(loginId1)
//                .build();
//        SignUpRequestDto userRequestDto2= SignUpRequestDto.builder()
//                .loginId(loginId2)
//                .build();
//
//        //when
//        Long saveId1=userService.signup(userRequestDto1);
//        Long saveId2=userService.signup(userRequestDto2);
//
//        //then
//        fail("예외가 발생해야 한다.");
//    }
//
//    @Test(expected = IllegalStateException.class)
//    public void 비밀번호_불일치_예외() throws Exception {
//        //given
//        String loginId="loginId";
//        String password="password";
//        String confirmPassword="confirmPassword";
//        String name="name";
//        String type="type";
//
//        SignUpRequestDto userRequestDto= SignUpRequestDto.builder()
//                .loginId(loginId)
//                .password(password)
//                .name(name)
//                .type(type)
//                .build();
//
//        //when
//        Long saveId=userService.signup(userRequestDto);
//
//        //Then
//        fail("비밀번호 불일치 예외 발생");
//    }
//
//    @BeforeEach
//    public void setup(){
//        mvc= MockMvcBuilders
//                .webAppContextSetup(this.context)
//                .apply(springSecurity())
//                .build();
//        User user= User.builder()
//                .loginId("id1")
//                .password("password")
//                .build();
//        userRepository.save(user);
//
//    }
//    @Test
//    public void 로그인()throws Exception{
//        //given
//        String loginId="id1";
//        String password="password";
//
//        //when
//        mvc.perform(formLogin().loginId(loginId).password(password))
//                .andDo(print())
//
//        //then
//
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//
//    }
//
//}
