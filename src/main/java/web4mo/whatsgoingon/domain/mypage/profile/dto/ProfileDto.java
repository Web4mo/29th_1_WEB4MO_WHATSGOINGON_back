package web4mo.whatsgoingon.domain.mypage.profile.dto;

import lombok.*;
import java.util.Set;

@Setter
@Getter
public class ProfileDto {
    private Long id;
    private String name;
    private String loginId;
    private String userType;
    private String assignAt;
    private Set<String> interests;
    private Set<String> keywords;
    private Set<String> media;
    private String profileImg;
}
