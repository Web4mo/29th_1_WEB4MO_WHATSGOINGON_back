package web4mo.whatsgoingon.domain.mypage.service;

import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web4mo.whatsgoingon.domain.mypage.dto.ProfileDto;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.service.UserService;

import java.awt.*;
import java.net.URL;


@Service
@AllArgsConstructor
public class ProfileService {
    private final UserService userService;

    public ProfileDto getProfile() {
        Member member = userService.getCurrentMember(); // 현재 로그인되어있는 정보
        return ProfileDto.builder()
                .id(member.getId()).name(member.getName()).loginId(member.getLoginId())
                .userType(member.getUserType()).assignAt(member.getAssignAt())
                .interests(null).keywords(null).media(null)
                .profileImg(member.getProfileImg()).build();
    }

//    @Transactional
//    public ProfileDto updateProfile(ProfileDto profileDto){
//        Member member = userService.getCurrentMember(); // 현재 로그인되어있는 정보
//        member.setType(profileDto.getUserType());
//        member.setInterests(profileDto.getInterests());
//        member.setKeywords(profileDto.getKeywords());
//        member.setMedia(profileDto.getMedia());
//
//        // 업데이트된 Member 저장
//        userService.updateMember(member);
//
//        return ProfileDto.builder()
//                .id(member.getId()).name(member.getName()).loginId(member.getLoginId())
//                .userType(member.getType()).assignAt(member.getAssignAt())
//                .interests(null).keywords(null).media(null)
//                .profileImg(member.getProfileImg()).build();
//    }

    public ProfileDto uploadImage(MultipartFile image) {
        if(image.isEmpty()) {
            throw new IllegalArgumentException("이미지가 없습니다.");
        }
        String storedFileName = S3Uploader.upload(image,"images");
        URL imageUrl = new URL(storedFileName);

        Member member = userService.getCurrentMember();
        ProfileImg profileImg = new ProfileImg(imageUrl);
        member.setProfileImg(profileImg);
        userService.updateMember(member);

        return ProfileDto.builder()
                .id(member.getId())
                .name(member.getName())
                .loginId(member.getLoginId())
                .userType(member.getUserType())
                .assignAt(member.getAssignAt())
                .interests(null)
                .keywords(null)
                .media(null)
                .profileImg(member.getProfileImg())
                .build();
    }

    public ProfileDto deleteImage() {
        // 이미지 삭제 로직
    }

}
