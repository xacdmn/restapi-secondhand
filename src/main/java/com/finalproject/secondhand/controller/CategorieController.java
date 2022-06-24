package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.categorie.CategorieDto;
import com.finalproject.secondhand.entity.Categories;
import com.finalproject.secondhand.service.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequiredArgsConstructor
public class CategorieController {

    @Autowired
    private final CategorieService categorieService;

    @Autowired
    ModelMapper modelMapper;

    @Operation(summary = "Add category")
    @PostMapping("/api/category/add")
    public ResponseEntity<Categories> postKategori(@RequestBody CategorieDto categorieDto){
        Categories categories = modelMapper.map(categorieDto, Categories.class);
        return new ResponseEntity<>(categorieService.addCategorie(categories), HttpStatus.CREATED);
    }

    @Operation(summary = "Find all category")
    @GetMapping("/api/category/get")
    public ResponseEntity<List<Categories>> getAllKategori(){
        return new ResponseEntity<>(categorieService.getAllCategorie(), HttpStatus.OK);
    }

    @Operation(summary = "Find category by categoryId")
    @GetMapping("/api/category/get/{categoryId}")
    public ResponseEntity<Optional<Categories>> getKategoriById(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categorieService.getCategorieById(categoryId), HttpStatus.OK);
    }

    @Operation(summary = "Update category by categoryId")
    @PutMapping("/api/category/update/{categoryId}")
    public ResponseEntity<Categories> updateKategori(@RequestBody CategorieDto categorieDto , @PathVariable Integer categoryId){
        Categories categories = modelMapper.map(categorieDto, Categories.class);
        return new ResponseEntity<>(categorieService.updateCategorie(categories, categoryId), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete category by categoryId")
    @DeleteMapping("/api/category/delete/{categoryId}")
    public ResponseEntity<String> deleteKategori(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categorieService.deleteCategorie(categoryId), HttpStatus.ACCEPTED);
    }

}
