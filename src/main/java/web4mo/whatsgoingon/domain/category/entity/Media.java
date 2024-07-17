package web4mo.whatsgoingon.domain.category.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter @Builder
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_d")
    private Long id;

    private String name;
    private String link;


}
