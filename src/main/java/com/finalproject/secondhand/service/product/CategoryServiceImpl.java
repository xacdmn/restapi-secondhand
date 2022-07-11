package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Category;
import com.finalproject.secondhand.enums.ECategory;
import com.finalproject.secondhand.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Bean
    public void addNewCategory() {

        for (ECategory eCategory : ECategory.values()) {
            try {
                Category category = categoryRepository.findByECategory(eCategory)
                        .orElseThrow(() -> new NullPointerException(
                                String.format("Category: %s not found", eCategory)));
                getLogger().info("{} is found", category);
            } catch (RuntimeException e) {
                getLogger().info(String.format("Category: %s not found." + " It will be create ...", eCategory.name()));

                Category category = new Category();
                category.setECategory(eCategory);
                categoryRepository.save(category);
            }
        }
    }

    @Override
    public Category loadCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NullPointerException("No category with id " + categoryId));
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}
