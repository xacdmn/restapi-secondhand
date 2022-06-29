package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Categories;
import com.finalproject.secondhand.enums.ECategory;
import com.finalproject.secondhand.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categoryRepository;

    public Optional<Categories> findByCategory(ECategory category) {
        return categoryRepository.findByCategory(category);
    }

    public List<Categories> findAll() {
        return categoryRepository.findAll();
    }

    public void save(Categories categories) {
        categoryRepository.save(categories);
    }

}
