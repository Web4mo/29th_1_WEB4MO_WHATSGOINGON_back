package web4mo.whatsgoingon.domain.mypage.service;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import web4mo.whatsgoingon.config.Authentication.PasswordEncoderConfig;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.domain.category.entity.Category;
import web4mo.whatsgoingon.domain.category.service.CategoryKeywordService;
import web4mo.whatsgoingon.domain.mypage.dto.ProfileDto;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProfileService {
    private final UserService userService;
    private final CategoryKeywordService categoryKeywordService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public ProfileDto getProfile() {
        Member member = userService.getCurrentMember(); // 현재 로그인되어있는 정보
        return ProfileDto.builder()
                .id(member.getId())
                .name(member.getName())
                .loginId(member.getLoginId())
                .userType(member.getUserType())
                .createAt(member.getCreatedAt())
                .interests(categoryKeywordService.userCategories(member))
                .keywords(categoryKeywordService.userKeywords(member))
                .media(categoryKeywordService.userMedium(member))
                .profileImg(member.getProfileImg())
                .build();
    }

    public ProfileDto updateProfile(ProfileDto profileDto){
        Member member = userService.getCurrentMember(); // 현재 로그인되어있는 정보
        if (member.getUserType() != null) {
            member.setUserType(profileDto.getUserType());
        }
        if (profileDto.getInterests() != null) {
            categoryKeywordService.updateUserCategories(member, profileDto.getInterests());
        }
        if (profileDto.getKeywords() != null) {
            categoryKeywordService.updateUserKeywords(member, profileDto.getKeywords());
        }
        if (profileDto.getMedia() != null) {
            categoryKeywordService.updateUserMedium(member, profileDto.getMedia());
        }

        //userService.updateProfile(member);

        return ProfileDto.builder()
                .id(member.getId())
                .name(member.getName())
                .loginId(member.getLoginId())
                .userType(member.getUserType())
                .createAt(member.getCreatedAt())
                .interests(categoryKeywordService.userCategories(member))
                .keywords(categoryKeywordService.userKeywords(member))
                .media(categoryKeywordService.userMedium(member))
                .profileImg(member.getProfileImg())
                .build();
    }

    public ProfileDto editPassword(ProfileDto profileDto){
        Member member = userService.getCurrentMember(); // 현재 로그인되어있는 정보

        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(profileDto.getCurrentPassword(), passwordEncoder.encode(member.getPassword()))) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 새로운 비밀번호 확인
        if (!profileDto.getNewPassword().equals(profileDto.getConfirmPassword())) {
            throw new IllegalArgumentException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        // 비밀번호 업데이트
        member.setPassword(passwordEncoder.encode(profileDto.getNewPassword())); // 비밀번호 인코딩
        //userService.updateProfile(member); // 회원 정보 업데이트

        return ProfileDto.builder()
                .id(member.getId())
                .name(member.getName())
                .loginId(member.getLoginId())
                .userType(member.getUserType())
                .createAt(member.getCreatedAt())
                .interests(categoryKeywordService.userCategories(member))
                .keywords(categoryKeywordService.userKeywords(member))
                .media(categoryKeywordService.userMedium(member))
                .profileImg(member.getProfileImg())
                .build();
    }

//    public ProfileDto uploadImage(MultipartFile image) {
//        if(image.isEmpty()) {
//            throw new IllegalArgumentException("이미지가 없습니다.");
//        }
//        String storedFileName = S3Uploader.upload(image,"images");
//        URL imageUrl = new URL(storedFileName);
//
//        Member member = userService.getCurrentMember();
//        ProfileImg profileImg = new ProfileImg(imageUrl);
//        member.setProfileImg(profileImg);
//        userService.updateMember(member);
//
//        return ProfileDto.builder()
//                .id(member.getId())
//                .name(member.getName())
//                .loginId(member.getLoginId())
//                .userType(member.getUserType())
//                .assignAt(member.getAssignAt())
//                .interests(null)
//                .keywords(null)
//                .media(null)
//                .profileImg(member.getProfileImg())
//                .build();
//    }
//
//    public ProfileDto deleteImage() {
//        // 이미지 삭제 로직
//    }

}
