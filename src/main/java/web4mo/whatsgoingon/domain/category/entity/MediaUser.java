package web4mo.whatsgoingon.domain.category.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class MediaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private Member user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media media;


}
