package web4mo.whatsgoingon.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LogInRequestDto {
    private String loginId;
    private String password;
}
