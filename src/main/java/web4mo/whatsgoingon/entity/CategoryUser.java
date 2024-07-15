package web4mo.whatsgoingon.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;


@Entity
@Table(name = "categoryUser")
@Getter @Builder
public class CategoryUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private Member user;

    @Enumerated(EnumType.STRING)
    private Category category;

}
