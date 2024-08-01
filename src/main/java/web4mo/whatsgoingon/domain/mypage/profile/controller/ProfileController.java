package web4mo.whatsgoingon.domain.mypage.profile.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.domain.mypage.profile.dto.ProfileDto;
import web4mo.whatsgoingon.domain.mypage.profile.service.ProfileService;
import web4mo.whatsgoingon.response.Response;

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

    @GetMapping("/")
    @ResponseStatus(OK)
    public Response getProfile() {
        ProfileDto profileDTO = profileService.getProfile();
        return success(FETCH_PROFILE, profileDTO);
    }

//    @PutMapping("/{loginId}")
//    @ResponseStatus(OK)
//    public Response updateProfile(@RequestBody ProfileDto profileDto) {
//        ProfileDto updatedProfile = profileService.updateProfile(profileDto);
//        return success(UPDATE_PROFILE, updatedProfile);
//    }
}
