package web4mo.whatsgoingon.domain.mypage.profile.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
public class ProfileDto {
    // Getters and setters
    private String name;
    private String id;
    private String userType;
    private String assignDate;
    private Set<String> interests;
    private Set<String> keywords;
    private Set<String> media;
    private String profileImg;

}
