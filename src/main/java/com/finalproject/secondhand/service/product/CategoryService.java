package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {

    Category loadCategoryById(Integer categoryId);
    List<Category> findAllCategory();

}
