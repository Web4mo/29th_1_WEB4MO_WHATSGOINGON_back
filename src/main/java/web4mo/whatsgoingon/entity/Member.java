package web4mo.whatsgoingon.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Column
    private LocalDate assignDate;
    @Column
    private String type;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<CategoryUser> categoryUser = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<KeywordUser> keywordUser = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<MediaUser> mediaUser = new ArrayList<>();

}
