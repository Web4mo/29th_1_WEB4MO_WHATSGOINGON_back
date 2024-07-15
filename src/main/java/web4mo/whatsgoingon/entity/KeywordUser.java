package web4mo.whatsgoingon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.apache.catalina.User;

@Entity
@Getter @Builder
public class KeywordUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private Member user;

    @ManyToOne
    @JoinColumn(name = "keyword_id",nullable = false)
    private Keyword keyword;

}
