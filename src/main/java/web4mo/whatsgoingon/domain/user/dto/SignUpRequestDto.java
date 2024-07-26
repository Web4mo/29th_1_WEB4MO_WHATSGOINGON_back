package web4mo.whatsgoingon.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web4mo.whatsgoingon.domain.user.entity.Member;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String loginId;
    private String password;
    private String confirmPassword;
    private String name;
    private String type;


    @Builder
    public SignUpRequestDto(String loginId, String password, String confirmPassword, String name, String type){
        this.loginId=loginId;
        this.password=password;
        this.confirmPassword=confirmPassword;
        this.name=name;
        this.type=type;
    }

    public Member toEntity(){
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .type(type)
                .build();
    }
}
