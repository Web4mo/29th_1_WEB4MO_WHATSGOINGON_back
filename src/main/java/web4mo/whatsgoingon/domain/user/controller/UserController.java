package web4mo.whatsgoingon.domain.user.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.config.Authentication.JwtTokenProvider;
import web4mo.whatsgoingon.config.Authentication.SecurityUtil;
import web4mo.whatsgoingon.domain.user.dto.LogInRequestDto;
import web4mo.whatsgoingon.domain.user.dto.SignUpRequestDto;
import web4mo.whatsgoingon.domain.user.dto.TokenDto;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.service.UserService;

@Tag(name = "UserController", description = "사용자 관리 API")
@Slf4j
@Controller
@RequiredArgsConstructor
@ResponseBody
public class UserController {

    private String id=null;
    private final UserService userService;

    @GetMapping("/")
    public String home(){
        return "Home";
    }


    //회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequestDto userFormDTO){
        userService.signup(userFormDTO);
       return ResponseEntity.ok("Signup Success");
    }

    // User 조회
    @GetMapping("/user/{id}")
    public Member findById(@PathVariable String id) {
        return userService.findOne(id);
    }

    //로그인
    @PostMapping("/auth/login")
    public ResponseEntity<TokenDto> login(@RequestBody LogInRequestDto logInRequestDto){
        log.info("request loginId ="+logInRequestDto.getLoginId()+"request password ="+logInRequestDto.getPassword());
        TokenDto jwtToken = userService.login(logInRequestDto);
        log.info("jwtToken accessToken="+jwtToken.getAccessToken());

        return ResponseEntity.ok(jwtToken);
    }

    //엑세스 토큰 재발급
    @PostMapping(" /auth/login")
    public ResponseEntity<?> reIssue(@RequestBody TokenDto tokenDto){
        userService.tokenReissue(tokenDto);
        return ResponseEntity.ok("Reissue Success");
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authrization") String token){

        return ResponseEntity.ok("Logout Success");
    }


    @PostMapping("/test")
    public Long test() {
        return SecurityUtil.getCurrentStudioId();
    }


}
