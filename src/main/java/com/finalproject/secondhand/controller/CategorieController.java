package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.categorie.CategorieDto;
import com.finalproject.secondhand.entity.Categories;
import com.finalproject.secondhand.service.CategorieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategorieController {

    @Autowired
    private final CategorieService categorieService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/category/add")
    public ResponseEntity<Categories> postKategori(@RequestBody CategorieDto categorieDto){
        Categories categories = modelMapper.map(categorieDto, Categories.class);
        return new ResponseEntity<>(categorieService.addCategorie(categories), HttpStatus.CREATED);
    }

    @GetMapping("/category/get")
    public ResponseEntity<List<Categories>> getAllKategori(){
        return new ResponseEntity<>(categorieService.getAllCategorie(), HttpStatus.OK);
    }

    @GetMapping("/category/get/{categoryId}")
    public ResponseEntity<Optional<Categories>> getKategoriById(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categorieService.getCategorieById(categoryId), HttpStatus.OK);
    }

    @PutMapping("/category/update/{categoryId}")
    public ResponseEntity<Categories> updateKategori(@RequestBody CategorieDto categorieDto , @PathVariable Integer categoryId){
        Categories categories = modelMapper.map(categorieDto, Categories.class);
        return new ResponseEntity<>(categorieService.updateCategorie(categories, categoryId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/category/delete/{categoryId}")
    public ResponseEntity<String> deleteKategori(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categorieService.deleteCategorie(categoryId), HttpStatus.ACCEPTED);
    }

}
