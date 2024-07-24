package web4mo.whatsgoingon.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import web4mo.whatsgoingon.domain.user.dto.LogInRequestDto;
import web4mo.whatsgoingon.domain.user.dto.SignUpRequestDto;
import web4mo.whatsgoingon.domain.user.dto.TokenDto;
import web4mo.whatsgoingon.domain.user.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
@ResponseBody
public class UserController {

    private final UserService userService;

    @GetMapping("/home")



    //회원가입
    @PostMapping("/auth/signUp")
    public String signup(SignUpRequestDto userFormDTO){
        userService.signup(userFormDTO);
       return "Success";
    }

    //로그인
    @PostMapping("/auth/logIn")
    public TokenDto login(@RequestBody LogInRequestDto logInRequestDto){
        TokenDto jwtToken = userService.login(logInRequestDto);
        log.info("request loginId ="+logInRequestDto.getLoginId()+"request password ="+logInRequestDto.getPassword());
        log.info("jwtToken accessToken="+jwtToken.getAccessToken());

        return jwtToken;
    }


}
