package web4mo.whatsgoingon.domain.article.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private String img;

    @Column(updatable = false)
    private LocalDate pubDate;

    @CreatedDate
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column
    private boolean crawling;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id",nullable = false)
    private Keyword keyword;


}
