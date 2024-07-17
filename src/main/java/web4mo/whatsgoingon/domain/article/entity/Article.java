package web4mo.whatsgoingon.domain.article.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import web4mo.whatsgoingon.domain.category.entity.Keyword;
import web4mo.whatsgoingon.domain.scrap.entity.Scrap;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String link;

    @Column
    private String img;

    @Column(updatable = false)
    private LocalDate pubDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column
    private boolean crawling;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id",nullable = false)
    private Keyword keyword;

    @OneToOne(mappedBy = "article",fetch = FetchType.LAZY)
    private Scrap scrap;

    @Builder
    public Article(String title, String link, String img,LocalDate pubDate ) {
        this.title = title;
        this.link = link;
        this.img = img;
        this.pubDate = pubDate;
    }

}
