package web4mo.whatsgoingon.domain.category.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import web4mo.whatsgoingon.domain.category.entity.Category;
import web4mo.whatsgoingon.domain.category.entity.CategoryKeyword;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
public class Keyword {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    @Column @Enumerated(EnumType.STRING)
    private Category category;

    private String name;

    @OneToMany(mappedBy = "keyword")
    private List<CategoryKeyword> categoryKeywords=new ArrayList<>();


}