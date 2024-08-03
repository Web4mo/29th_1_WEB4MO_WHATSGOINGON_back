package web4mo.whatsgoingon.domain.mypage.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.domain.category.entity.Category;
import web4mo.whatsgoingon.domain.category.entity.Media;
import web4mo.whatsgoingon.domain.mypage.dto.EditPasswordDto;
import web4mo.whatsgoingon.domain.mypage.dto.ProfileDto;
import web4mo.whatsgoingon.domain.mypage.dto.UpdateProfileDto;
import web4mo.whatsgoingon.domain.mypage.service.ProfileService;
import web4mo.whatsgoingon.response.Response;

import java.util.ArrayList;
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

    @Transactional
    @GetMapping("/getProfile")
    @ResponseStatus(OK)
    public Response getProfile() {
        ProfileDto profileDTO = profileService.getProfile();
        return success(FETCH_PROFILE, profileDTO);
    }

    @PutMapping("/updateProfile")
    @ResponseStatus(OK)
    @Transactional
    public Response updateProfile(@RequestBody UpdateProfileDto updateProfileDto) {
        ProfileDto updatedProfile = profileService.updateProfile(updateProfileDto);
        return success(UPDATE_PROFILE, updatedProfile);
    }

    @PutMapping("/editpassword")
    @ResponseStatus(OK)
    @Transactional
    public Response editPassword(@RequestBody EditPasswordDto editPasswordDto){
        ProfileDto updatedProfile = profileService.editPassword(editPasswordDto);
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
