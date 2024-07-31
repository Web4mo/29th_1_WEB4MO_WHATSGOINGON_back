package web4mo.whatsgoingon.domain.mypage.profile.entity;

import lombok.*;
import jakarta.persistence.*;
import web4mo.whatsgoingon.domain.BaseTime;
import web4mo.whatsgoingon.domain.user.entity.Member;
import java.util.Set;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String loginId;
    private String password;
    private String name;
    private String userType;
    private String profileImg; // s3로 받아오는 거 해야 함
    // assignDate -> basetime 상속

    @ElementCollection
    @CollectionTable(name = "profile_interests", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "interest")
    private Set<String> interests;

    @ElementCollection
    @CollectionTable(name = "profile_keywords", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "keyword")
    private Set<String> keywords;

    @ElementCollection
    @CollectionTable(name = "profile_media", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "media")
    private Set<String> media;

//    public void updateProfile(String userType, Set<String> interests, String keywords, Set media) {
//        this.userType = userType;
//        this.interests = interests;
//    }
//    public void updatePassword(String password) {
//        this.password = password;
//    }

}
