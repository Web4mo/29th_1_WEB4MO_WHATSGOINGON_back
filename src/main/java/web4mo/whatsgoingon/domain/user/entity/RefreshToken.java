package web4mo.whatsgoingon.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refresh_token_id")
    private Long refreshTokenId;

    @Column(name="refresh_token", nullable = false)
    private String refreshToken;

    @Column(name="user_id", nullable = false)
    private String userId;


//    public void updateRefreshToken(String token) {
//        this.refreshToken = token;
//    }
}