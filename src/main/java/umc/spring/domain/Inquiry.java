package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.InquiryStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(nullable = false, length = 20)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String type;
    @Enumerated(EnumType.STRING)
    private InquiryStatus status;
    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private List<InquiryImage> inquiryImageList = new ArrayList<>();
}
