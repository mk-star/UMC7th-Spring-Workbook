package umc.spring.service.FoodCategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodCategoryQueryServiceImpl implements FoodCategoryQueryService {
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean isFoodCategoryExist(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
