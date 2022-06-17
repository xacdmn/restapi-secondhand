package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Categories;
import com.finalproject.secondhand.repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private final CategorieRepository categorieRepository;

    @Override
    public List<Categories> getAllKategori() {
        return categorieRepository.findAll();
    }

    @Override
    public Categories postKategori(Categories body) {
        categorieRepository.save(body);
        return body;
    }

    @Override
    public Optional<Categories> getKategoriById(Integer categoryId) {
        return categorieRepository.findById(categoryId);
    }

    @Override
    public Categories updateKategori(Categories body, Integer categoryId) {
        Categories categories = categorieRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found!"));
        categories.setCategoryName(body.getCategoryName());
        return categorieRepository.save(categories);
    }

    @Override
    public String deleteKategori(Integer categoryId) {
        Categories categories = categorieRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found!"));
        String result = categories.getCategoryName();
        categorieRepository.deleteById(categoryId);
        return "Category "+ result +" has been deleted";
    }

}
