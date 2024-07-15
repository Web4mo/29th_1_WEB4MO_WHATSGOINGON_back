package web4mo.whatsgoingon.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(length = 30, nullable = false)
    private String id;

    @Column
    private String password;

    @Column
    private LocalDateTime assignDate;

    @Column
    private String name;

    @Column
    private String profileImg;

    @Column
    private String type;
}
