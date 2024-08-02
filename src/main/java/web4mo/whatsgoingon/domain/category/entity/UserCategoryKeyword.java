package web4mo.whatsgoingon.domain.category.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.domain.user.entity.Member;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCategoryKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_category_keyword_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private Member member;

    @Column(name = "keyword")
    private String keyword;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;



}
