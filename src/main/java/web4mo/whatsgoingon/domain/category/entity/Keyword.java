package web4mo.whatsgoingon.domain.category.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
public class Keyword {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "keyword",cascade = CascadeType.ALL)
    private List<UserCategoryKeyword> userCategoryKeywords = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

}