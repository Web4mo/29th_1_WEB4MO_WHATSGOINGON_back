package web4mo.whatsgoingon.config.Authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import web4mo.whatsgoingon.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.domain.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByLoginId(username)
                .map(this::createUserDetails)
                .orElseThrow(()-> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member){
        return  User.builder()
                .username(member.getLoginId())
                .password(member.getPassword())
                .build();
    }
}


