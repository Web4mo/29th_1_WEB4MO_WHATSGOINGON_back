package web4mo.whatsgoingon.domain.category.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import web4mo.whatsgoingon.domain.user.entity.User;

@Entity
@Getter @Builder
public class UserCategoryKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_category_keyword_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id",nullable = false)
    private Keyword keyword;

}
