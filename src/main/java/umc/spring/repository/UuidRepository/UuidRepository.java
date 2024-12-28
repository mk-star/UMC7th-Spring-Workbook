package umc.spring.repository.UuidRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
