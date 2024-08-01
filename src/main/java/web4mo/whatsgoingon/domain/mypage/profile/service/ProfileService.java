package web4mo.whatsgoingon.domain.mypage.profile.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.domain.mypage.profile.dto.ProfileDto;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.service.UserService;


@Service
@AllArgsConstructor
public class ProfileService {
    private final UserService userService;


    public ProfileDto getProfile() {
        Member member = userService.getCurrentMember(); // 현재 로그인되어있는 정보
        return ProfileDto.builder()
                .id(member.getId()).name(member.getName()).loginId(member.getLoginId())
                .userType(member.getType()).assignAt(member.getAssignAt())
                .interests(null).keywords(null).media(null)
                .profileImg(member.getProfileImg()).build();
    }


//    public ProfileDto updateProfile(Long id, ProfileDto profileDto) {
//        Optional<Profile> profileOpt = profileRepository.findById(id);
//        if (profileOpt.isPresent()) {
//            Profile profile = profileOpt.get();
//            profile.setUserType(profileDto.getUserType());
//            profile.setInterests(profileDto.getInterests());
//            profile.setKeywords(profileDto.getKeywords());
//            profile.setMedia(profileDto.getMedia());
//
//            profileRepository.save(profile);
//
//            return profileDto;
//        } else {
//            throw new RuntimeException("Profile not found");
//        }
//    }
}
