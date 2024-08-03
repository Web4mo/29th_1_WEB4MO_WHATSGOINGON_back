package web4mo.whatsgoingon.domain.mypage.service;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import web4mo.whatsgoingon.config.Authentication.PasswordEncoderConfig;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.domain.category.entity.Category;
import web4mo.whatsgoingon.domain.category.service.CategoryKeywordService;
import web4mo.whatsgoingon.domain.mypage.dto.EditPasswordDto;
import web4mo.whatsgoingon.domain.mypage.dto.ProfileDto;
import web4mo.whatsgoingon.domain.mypage.dto.UpdateProfileDto;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProfileService {
    private final UserService userService;
    private final CategoryKeywordService categoryKeywordService;
    private final S3Uploader s3Uploader;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Transactional
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

    @Transactional
    public ProfileDto updateProfile(UpdateProfileDto updateProfileDto){
        Member member = userService.getCurrentMember(); // 현재 로그인되어있는 정보
        if (member.getUserType() != null) {
            member.updateUserType(updateProfileDto.getUserType());
        }
        if (updateProfileDto.getUserCategories() != null) {
            categoryKeywordService.updateUserCategories(member, updateProfileDto.getUserCategories());
        }
        if (updateProfileDto.getUserKeywords() != null) {
            categoryKeywordService.updateUserKeywords(member, updateProfileDto.getUserKeywords());
        }
        if (updateProfileDto.getUserMedium() != null) {
            categoryKeywordService.updateUserMedium(member, updateProfileDto.getUserMedium());
        }

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

    public ProfileDto editPassword(EditPasswordDto profileDto){
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
        member.updatePassword(profileDto.getNewPassword()); // 비밀번호 인코딩
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

    //이미지 업로드 및 수정
    public ProfileDto uploadImage(MultipartFile image,String dir) throws IOException {
        if(image.isEmpty()) {
            throw new IllegalArgumentException("이미지가 없습니다.");
        }
        String old= String.valueOf(getProfile().getProfileImg());
        URL newURL=new URL(s3Uploader.updateFile(image,old,dir));
        Member member = userService.getCurrentMember();
        member.updateProfilImg(newURL);
//        String storedFileName = s3Uploader.upload(image,"images");
//        URL imageUrl = new URL(storedFileName);
//
//        Member member = userService.getCurrentMember();
//        ProfileImg profileImg = new ProfileImg(imageUrl);
//        member.setProfileImg(profileImg);
//        userService.updateMember(member);

        return ProfileDto.builder()
                .id(member.getId())
                .name(member.getName())
                .loginId(member.getLoginId())
                .userType(member.getUserType())
                .createAt(member.getCreatedAt())
                .interests(null)
                .keywords(null)
                .media(null)
                .profileImg(member.getProfileImg())
                .build();
    }


    // 이미지 삭제 로직
    public ProfileDto deleteImage() throws IOException {
        String old= String.valueOf(getProfile().getProfileImg());
        s3Uploader.updateFile(null,old,null);
        ProfileDto.builder().profileImg(null).build();
        Member member = userService.getCurrentMember();
        member.updateProfilImg(null);
        return null;
    }

}
