package web4mo.whatsgoingon.config.Authentication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import web4mo.whatsgoingon.domain.user.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.domain.user.repository.UserRepository;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("loginID:"+username);
        log.info(String.valueOf(userRepository.findByLoginId(username)));
        //Member member=userRepository.findByLoginId(username).get();
        return userRepository.findByLoginId(username)
                .map(this::createUserDetails)
                .orElseThrow(()-> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member){
        return  User.builder()
                .username(member.getLoginId())
                .password(passwordEncoder.encode(member.getPassword()))
                //.password(member.getPassword())
                .build();
    }
}


