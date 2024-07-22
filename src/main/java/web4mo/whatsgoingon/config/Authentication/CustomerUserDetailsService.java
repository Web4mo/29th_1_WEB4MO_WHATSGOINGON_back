//package web4mo.whatsgoingon.config.Authentication;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import web4mo.whatsgoingon.domain.user.entity.User;
//import web4mo.whatsgoingon.domain.user.repository.UserRepository;
//
//@Service
//@RequiredArgsConstructor
//public class CustomerUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByName(String userid) throws UsernameNotFoundException{
//        return userRepository.findByLoginId(userid)
//                .map(this::createUserDetails)
//                .orElseThrow(()-> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
//    }
//
//    private UserDetails createUserDetails(User user){
//        return (UserDetails) User.builder()
//                .loginId(user.getLoginId())
//                .password(user.getPassword())
//                .build();
//    }
//}
//
//
