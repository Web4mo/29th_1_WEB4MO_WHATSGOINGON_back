package web4mo.whatsgoingon.domain.user.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.config.Authentication.JwtTokenProvider;
import web4mo.whatsgoingon.config.Authentication.SecurityUtil;
import web4mo.whatsgoingon.domain.user.dto.LogInRequestDto;
import web4mo.whatsgoingon.domain.user.dto.SignUpRequestDto;
import web4mo.whatsgoingon.domain.user.dto.TokenDto;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.service.UserService;
import web4mo.whatsgoingon.response.Response;
import static web4mo.whatsgoingon.response.Message.*;

import static web4mo.whatsgoingon.response.Response.success;

@Tag(name = "UserController", description = "사용자 관리 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@ResponseBody
public class UserController {

    private String id=null;
    private final UserService userService;

    @GetMapping("/getcurrentMemberTest")
    public Response getMember(){
        userService.getCurrentMember();
        return success("Test success");
    }

    @GetMapping("/")
    public String home(){
        return "Home";
    }


    //회원가입
    @PostMapping("/auth/signup")
    public Response signup(@RequestBody SignUpRequestDto userFormDTO){
        userService.signup(userFormDTO);
       return success(SIGN_UP);
    }

    // User 조회
//    @GetMapping("/user/{id}")
//    public Member findById(@PathVariable String id) {
//        return
//    }

    //로그인
    @PostMapping("/auth/login")
    public Response login(@RequestBody LogInRequestDto logInRequestDto){
        log.info("request loginId ="+logInRequestDto.getLoginId()+"request password ="+logInRequestDto.getPassword());
        TokenDto jwtToken = userService.login(logInRequestDto);
        log.info("jwtToken accessToken="+jwtToken.getAccessToken());

        return success(LOG_IN,jwtToken);
    }

    //엑세스 토큰 재발급
    @PostMapping(" /auth/login")
    public Response reIssue(@RequestBody TokenDto tokenDto){
        userService.tokenReissue(tokenDto);
        return success("성공했습니다.");
    }

    //로그아웃
    @PostMapping("/logout")
    public Response logout(@RequestHeader("Authrization") String token){

        return success(LOG_OUT);
    }


    @PostMapping("/test")
    public Long test() {
        return SecurityUtil.getCurrentStudioId();
    }


}
