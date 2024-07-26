package web4mo.whatsgoingon.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import web4mo.whatsgoingon.config.Authentication.JwtTokenProvider;
import web4mo.whatsgoingon.domain.user.dto.GetMemberTestDto;
import web4mo.whatsgoingon.domain.user.dto.LogInRequestDto;
import web4mo.whatsgoingon.domain.user.dto.SignUpRequestDto;
import web4mo.whatsgoingon.domain.user.dto.TokenDto;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.repository.UserRepository;

import java.util.Optional;

@Slf4j
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
        log.info("pw:"+password1);
        log.info("confrimpw: "+password2);
        if (password1 == null){
            throw new IllegalStateException("비밀번호가 없습니다.");
        }
        if( !password1.equals(password2)){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }


    /*
     *로그인
     */
    @Transactional
    public TokenDto login(LogInRequestDto logInRequestDto){
        Optional<Member> optionalUser = userRepository.findByLoginId(logInRequestDto.getLoginId());
        log.info("로그인 진행중 ....");
        if(optionalUser.isEmpty()){
            throw new IllegalStateException("회원이 아닙니다.");
        }
        Member member =optionalUser.get();
        if(!member.getPassword().equals(logInRequestDto.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }


        //실제 인증
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(logInRequestDto.getLoginId(),logInRequestDto.getPassword());
        log.info("인증토큰: "+String.valueOf(authenticationToken));
        log.info("logInRequestDto.getLoginId(),logInRequestDto.getPassword() : "+logInRequestDto.getLoginId(),logInRequestDto.getPassword());
        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication=authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        if(!authentication.isAuthenticated())
            log.info("인증 실패");
        String userId= authentication.getName();
        log.info("loginId"+userId);

        log.info("인증: "+String.valueOf(authentication));
        //인증 정보 기반으로 jwt 토큰 생성
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateTokenDto(authentication);
    }

    //refresh 토큰 재발급
    public TokenDto tokenReissue(TokenDto tokenDto){
        String refreshToken =tokenDto.getRefreshToken();
        Authentication authentication=jwtTokenProvider.getAuthentication(refreshToken);
        //String userId= authentication.getName();

        //log.info("loginId"+userId);
        if(StringUtils.hasText(refreshToken ) && jwtTokenProvider.validateToken(refreshToken)){
            log.info("getting new access Token");
            String newAccessToken = jwtTokenProvider.createAccessToken(authentication);
            return TokenDto.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken)
                    .build();
        }else { //refresh token 만료
            //refreshTokenRepository.deleteByEmail(email);
            //RT 만료됐다는걸 알리는 예외 발생 -> 로그인으로 유도
            //throw new RefreshTokenExpired();
        }

        return tokenDto;
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

    public Optional<GetMemberTestDto> getCurrentMember() {

        Optional<Member> member = userRepository.findByLoginId(SecurityContextHolder.getContext().getAuthentication().getName());
        
        return member;
    }
}
