package web4mo.whatsgoingon.domain.mypage.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.domain.category.entity.Category;
import web4mo.whatsgoingon.domain.category.entity.Media;
import web4mo.whatsgoingon.domain.mypage.dto.ProfileDto;
import web4mo.whatsgoingon.domain.mypage.service.ProfileService;
import web4mo.whatsgoingon.response.Response;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static web4mo.whatsgoingon.response.Message.*;
import static web4mo.whatsgoingon.response.Response.success;

@Tag(name = "Profile Controller")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/getProfile")
    @ResponseStatus(OK)
    public Response getProfile() {
        ProfileDto profileDTO = profileService.getProfile();
        return success(FETCH_PROFILE, profileDTO);
    }

    @PutMapping("/updateProfile")
    @ResponseStatus(OK)
    @Transactional
    public Response updateProfile(@RequestParam String userType,
                                  @RequestParam List<Category> userCategories,
                                  @RequestParam List<String> userKeywords,
                                  @RequestParam List<Media> userMedium) {
        ProfileDto profileDto = ProfileDto.builder()
                .userType(userType)
                .interests(userCategories)
                .keywords(userKeywords)
                .media(userMedium)
                .build();

        ProfileDto updatedProfile = profileService.updateProfile(profileDto);
        return success(UPDATE_PROFILE, updatedProfile);
    }

    @PutMapping("/editpassword")
    @ResponseStatus(OK)
    @Transactional
    public Response editPassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword){
        ProfileDto profileDto = ProfileDto.builder()
                .currentPassword(currentPassword)
                .newPassword(newPassword)
                .confirmPassword(confirmPassword)
                .build();

        ProfileDto updatedProfile = profileService.editPassword(profileDto);
        return success(EDIT_PASSWORD, updatedProfile);
    }


//    @PostMapping("/")
//    @ResponseStatus(OK)
//    public Response uploadImage(@RequestParam("image") MultipartFile image) {
//        ProfileDto uploadImage = profileService.uploadImage(image);
//        return success(UPLOAD_IMG, uploadImage);
//    }
//
//    @DeleteMapping("/")
//    @ResponseStatus(OK)
//    public Response deleteImage() {
//        ProfileDto deleteImage = profileService.deleteImage();
//        return success(DELETE_IMG, deleteImage);
//    }

}
