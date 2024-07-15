package web4mo.whatsgoingon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;

    @Column
    private Long articleId;

    @Column
    private Long folderId;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Column(columnDefinition = "TEXT")
    private String articleSummary;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime modifyDate;

}
