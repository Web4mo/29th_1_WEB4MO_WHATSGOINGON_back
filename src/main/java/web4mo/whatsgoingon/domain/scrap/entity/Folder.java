package web4mo.whatsgoingon.domain.scrap.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folderId;

    @Column
    private Long userId;

    @Column
    private String name;

    @Column
    private LocalDateTime modifyDate;
}
