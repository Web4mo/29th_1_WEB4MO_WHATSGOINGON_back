package web4mo.whatsgoingon.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleId;

    @Column
    private String title;

    @Column
    private String link;

    @Column
    private String img;

    @CreatedDate // CreationTimestamp?
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate //UpdateTimestamp?
    @Column
    private LocalDateTime updatedAt;

    @Column
    private boolean crawling;

    @Column
    private Long categoryId;

    @Column
    private Long keywordId;
}
