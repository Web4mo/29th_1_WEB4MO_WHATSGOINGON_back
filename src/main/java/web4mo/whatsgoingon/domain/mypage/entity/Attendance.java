package web4mo.whatsgoingon.domain.mypage.entity;

import jakarta.persistence.*;
import lombok.*;
import web4mo.whatsgoingon.domain.BaseTime;
import web4mo.whatsgoingon.domain.user.entity.Member;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(nullable = false)
    private LocalDate attendDate;

}
