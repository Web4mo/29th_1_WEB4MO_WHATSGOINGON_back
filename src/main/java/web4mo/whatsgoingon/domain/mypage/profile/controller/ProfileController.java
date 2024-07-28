package web4mo.whatsgoingon.domain.mypage.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web4mo.whatsgoingon.domain.mypage.profile.dto.ProfileDto;
import web4mo.whatsgoingon.domain.mypage.profile.service.ViewProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ViewProfileService ViewprofileService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        try {
            ProfileDto profileDTO = ViewprofileService.getProfileById(id);
            return ResponseEntity.ok().body(new ApiResponse(200, true, "사용자 정보 불러오기 성공했습니다.", profileDTO));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse(404, false, "사용자 정보를 찾을 수 없습니다.", null));
        }
    }
}

class ApiResponse {
    private int status;
    private boolean success;
    private String message;
    private Object data;

    public ApiResponse(int status, boolean success, String message, Object data) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
