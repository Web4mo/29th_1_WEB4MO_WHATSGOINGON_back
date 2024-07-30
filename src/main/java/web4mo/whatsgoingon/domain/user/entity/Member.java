package web4mo.whatsgoingon.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String profileImg;

    @Column
    private String type;
}
