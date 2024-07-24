package web4mo.whatsgoingon.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.config.Authentication.JwtTokenProvider;
import web4mo.whatsgoingon.domain.user.dto.LogInRequestDto;
import web4mo.whatsgoingon.domain.user.dto.SignUpRequestDto;
import web4mo.whatsgoingon.domain.user.dto.TokenDto;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    /*
    *회원가입
     */
    @Transactional
    public String signup(SignUpRequestDto signUpRequestDto){
        validateDuplicateUser(signUpRequestDto.getLoginId());
        isPasswordMatching(signUpRequestDto.getPassword(), signUpRequestDto.getConfirmPassword());

        return userRepository.save(signUpRequestDto.toEntity()).getLoginId();
    }

    //loginId 중복 체크
    private void validateDuplicateUser(String loginId) {
        if (userRepository.existsByLoginId(loginId)){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //비밀번호 일치 확인
    private void isPasswordMatching(String password1, String password2){
        if(password1 == null || !password1.equals(password2)){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }


    /*
     *로그인
     */
    @Transactional
    public TokenDto login(LogInRequestDto logInRequestDto){
        Optional<Member> optionalUser = userRepository.findByLoginId(logInRequestDto.getLoginId());
        if(optionalUser.isEmpty()){
            throw new IllegalStateException("회원이 아닙니다.");
        }
        Member member =optionalUser.get();
        if(!member.getPassword().equals(logInRequestDto.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        //실제 검증
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(logInRequestDto.getLoginId(),logInRequestDto.getPassword());
        Authentication authentication=authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //인증 정보 기반으로 jwt 토큰 생성

        return jwtTokenProvider.generateTokenDto(authentication);
    }



    /*
     *전체 회원 조회
     */
    public Member findOne(String loginId){
        Optional<Member> findUsers= userRepository.findByLoginId(loginId);
        if (findUsers.isEmpty()){
            throw new IllegalStateException("회원이 아닙니다.");
        }
        return findUsers.get();
    }
}
