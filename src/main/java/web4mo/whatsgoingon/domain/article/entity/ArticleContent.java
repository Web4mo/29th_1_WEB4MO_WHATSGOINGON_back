package web4mo.whatsgoingon.domain.article.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Table(name = "articleContent")
public class ArticleContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleContentId;

    @Column
    private Long articleId;

    @Column
    private String content;
}
