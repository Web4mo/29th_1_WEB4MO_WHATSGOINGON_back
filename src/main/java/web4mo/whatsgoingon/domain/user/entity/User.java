package web4mo.whatsgoingon.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import web4mo.whatsgoingon.domain.category.entity.MediaUser;
import web4mo.whatsgoingon.domain.category.entity.UserCategoryKeyword;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",length = 30, nullable = false)
    private Long id;

    @Column
    private String password;

    @Column
    private LocalDateTime assignDate;

    @Column
    private String name;

    @Column
    private String profileImg;

    @Column
    private String type;

    @OneToMany(mappedBy = "user")
    private List<UserCategoryKeyword> userCategoryKeywords =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<MediaUser> mediaUsers=new ArrayList<>();

}
