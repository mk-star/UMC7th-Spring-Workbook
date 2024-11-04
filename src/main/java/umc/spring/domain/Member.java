package umc.spring.domain;


import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.mapping.MemberAgree;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;
    private int age;
    @Column(nullable = false, length = 40)
    private String address;
    @Column(nullable = false, length = 40)
    private String specAddress;
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime inactiveDate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private SocialType socialType;
    @Column(nullable = false, length = 50)
    private String email;
    private Integer point;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Inquiry> inquiryList = new ArrayList<>();
}