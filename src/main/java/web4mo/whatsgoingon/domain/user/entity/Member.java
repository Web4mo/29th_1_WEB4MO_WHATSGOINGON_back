package web4mo.whatsgoingon.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 30, nullable = false)
    private Long id;

    @Column
    private String loginId;
    @Column
    private String password;

    @Column
    private LocalDateTime assignAt;

    @Column
    private String name;

    @Enumerated
    private Role role;

    @Column
    private String profileImg;

    @Column
    private String type;
}
