package umc.spring.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.MemberMission;

public interface MissionRepository extends JpaRepository<MemberMission, Long>, MissionRepositoryCustom {
}
