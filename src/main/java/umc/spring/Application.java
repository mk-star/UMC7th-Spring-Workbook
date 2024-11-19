package umc.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.MissionService.MissionQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			MissionQueryService missionQueryService = context.getBean(MissionQueryService.class);
//			Pageable pageable = PageRequest.of(0, 10);
//			// 파라미터 값 설정
//			Long memberId = 1L;
//			MissionStatus status = MissionStatus.valueOf("NOT_STARTED");
//			Long lastMissionId = 10L;
//			String regionName = "서울";
//
//			//쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findNotStartedMissionByMemberIdAndStatusAndRegionName with parameters:");
//			System.out.println("Member ID: " + memberId);
//			System.out.println("Status: " + status);
//			System.out.println("Region Name: " + regionName);
//
//			missionQueryService.findNotStartedMissionByMemberIdAndStatusAndRegionName(memberId, status, regionName, lastMissionId, pageable)
//					.forEach(System.out::println);
//		};
//	}
}