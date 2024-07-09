package web4mo.whatsgoingon.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(length = 30, nullable = false)
    private String id;

    @Column
    private String password;

    @Column
    private String name;
}
