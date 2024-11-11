package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
    @Column(nullable = false, columnDefinition = "VARCHAR(15) DEFAULT 'NOT_STARTED'")
    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @Override
    public String toString() {
        return "MemberMission{" +
                "id=" + id +
                ", name=" + mission.getStore().getName() +
                ", deadline=" + ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadline()) +
                ", price=" + mission.getPrice() +
                ", reward=" + mission.getReward() +
                ", status=" + status +
                '}';
    }
}
