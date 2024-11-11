package umc.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.ReviewService.ReviewQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);

			Long member_id = 1L;
			Long store_id = 1L;
			float score = 4.2F;
			String body = "짱 맛있다!";

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing insertReview with parameters:");
			System.out.println("Member ID: " + member_id);
			System.out.println("Store ID: " + store_id);
			System.out.println("Score: " + score);
			System.out.println("Body: " + body);

			reviewService.insertReview(member_id, store_id, body, score);
		};
	}
}