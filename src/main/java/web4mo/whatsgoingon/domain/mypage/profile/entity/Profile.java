package web4mo.whatsgoingon.domain.mypage.profile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Setter
@Getter
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String userType;
    private LocalDate assignDate;
    private String profileImg;

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

}
