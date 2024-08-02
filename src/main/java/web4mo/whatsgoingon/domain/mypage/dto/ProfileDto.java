package web4mo.whatsgoingon.domain.mypage.dto;

import lombok.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
public class ProfileDto {
    private Long id;
    private String name;
    private String loginId;
    private String userType;
    private String createAt; //assignDate
    private Set<String> interests;
    private Set<String> keywords;
    private Set<String> media;
    private URL profileImg;
}
