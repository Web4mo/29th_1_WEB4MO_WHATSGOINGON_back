package web4mo.whatsgoingon.domain.category.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import web4mo.whatsgoingon.domain.user.entity.User;


@Entity
@Table(name = "categoryUser")
@Getter @Builder
public class CategoryUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

}
