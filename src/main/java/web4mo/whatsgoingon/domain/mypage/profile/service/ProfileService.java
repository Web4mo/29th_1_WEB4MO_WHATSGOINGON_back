package web4mo.whatsgoingon.domain.mypage.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.domain.mypage.profile.dto.ProfileDto;
import web4mo.whatsgoingon.domain.mypage.profile.entity.Profile;
import web4mo.whatsgoingon.domain.mypage.profile.repository.ProfileRepository;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // loginId를 파라미터로
    public ProfileDto getProfileByLoginId(String loginId) {
        Optional<Profile> profileOpt = profileRepository.findByLoginId(loginId);
        if (profileOpt.isPresent()) {
            Profile profile = profileOpt.get();
            ProfileDto profileDTO = new ProfileDto();
            profileDTO.setName(profile.getName());
            profileDTO.setLoginId(profile.getLoginId());
            profileDTO.setUserType(profile.getUserType());
            //profileDTO.setAssignDate(profile.getAssignDate().toString());
            profileDTO.setInterests(profile.getInterests());
            profileDTO.setKeywords(profile.getKeywords());
            profileDTO.setMedia(profile.getMedia());
            profileDTO.setProfileImg(profile.getProfileImg());
            return profileDTO;
        } else {
            throw new RuntimeException("Profile not found");
        }
    }

    // id를 파라미터로
//    public ProfileDto getProfileById(Long id) {
//        Optional<Profile> profileOpt = profileRepository.findById(id);
//        if (profileOpt.isPresent()) {
//            Profile profile = profileOpt.get();
//            ProfileDto profileDTO = new ProfileDto();
//            profileDTO.setName(profile.getName());
//            profileDTO.setLoginId(profile.getLoginId());
//            profileDTO.setUserType(profile.getUserType());
//            //profileDTO.setAssignDate(profile.getAssignDate().toString());
//            profileDTO.setInterests(profile.getInterests());
//            profileDTO.setKeywords(profile.getKeywords());
//            profileDTO.setMedia(profile.getMedia());
//            profileDTO.setProfileImg(profile.getProfileImg());
//            return profileDTO;
//        } else {
//            throw new RuntimeException("Profile not found");
//        }
//    }



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
