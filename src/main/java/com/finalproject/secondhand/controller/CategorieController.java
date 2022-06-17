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
@RequestMapping("/category")
public class CategorieController {

    @Autowired
    private final CategorieService categorieService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<Categories> postKategori(@RequestBody CategorieDto categorieDto){
        Categories categories = modelMapper.map(categorieDto, Categories.class);
        return new ResponseEntity<>(categorieService.postKategori(categories), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Categories>> getAllKategori(){
        return new ResponseEntity<>(categorieService.getAllKategori(), HttpStatus.OK);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<Optional<Categories>> getKategoriById(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categorieService.getKategoriById(categoryId), HttpStatus.OK);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Categories> updateKategori(@RequestBody CategorieDto categorieDto, @PathVariable Integer categoryId){
        Categories categories = modelMapper.map(categorieDto, Categories.class);
        return new ResponseEntity<>(categorieService.updateKategori(categories, categoryId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteKategori(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categorieService.deleteKategori(categoryId), HttpStatus.ACCEPTED);
    }

}
