package web4mo.whatsgoingon.domain.mypage.profile.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import web4mo.whatsgoingon.domain.BaseTime;
import java.time.LocalDate;
import java.util.Set;


@Setter
@Getter
@Entity
public class Profile extends BaseTime {
    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String loginId;
    private String name;
    private String userType;
    private LocalDate assignDate = LocalDate.now();
    private String profileImg; // s3로 받아오는 거 해야 함

    @ElementCollection
    @CollectionTable(name = "profile_interests", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "interest")
    private Set<String> interests;

    @ElementCollection
    @CollectionTable(name = "profile_keywords", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "keyword")
    private Set<String> keywords;

    @ElementCollection
    @CollectionTable(name = "profile_media", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "media")
    private Set<String> media;

}
