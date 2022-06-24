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
public class CategorieServiceImpl implements CategorieService{

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Categories> getAllCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public Categories addCategorie(Categories body) {
        categorieRepository.save(body);
        return body;
    }

    @Override
    public Optional<Categories> getCategorieById(Integer categoryId) {
        return categorieRepository.findById(categoryId);
    }

    @Override
    public Categories updateCategorie(Categories body, Integer categoryId) {
        Categories categories = categorieRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Kategori tidak ditemukan!"));
        categories.setCategoryName(body.getCategoryName());
        return categorieRepository.save(categories);
    }

    @Override
    public String deleteCategorie(Integer categoryId) {
        Categories categories = categorieRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Kategori tidak ditemukan!"));
        String result = categories.getCategoryName();
        categorieRepository.deleteById(categoryId);
        return "Kategori " + result + " telah dihapus";
    }

}
