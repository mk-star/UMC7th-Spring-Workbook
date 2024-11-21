package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer reward;
    @Column(nullable = false)
    private LocalDate deadline;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String missionSpec;
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public void setStore(Store store) {
        if(this.store != null)
            store.getMissionList().remove(this);
        this.store = store;
        store.getMissionList().add(this);
    }

    @Override
    public String toString() {
        return "MemberMission{" +
                "id=" + id +
                ", name=" + store.getName() +
                ", deadline=" + ChronoUnit.DAYS.between(LocalDate.now(), deadline) +
                ", price=" + price +
                ", reward=" + reward +
                '}';
    }
}
