package com.finalproject.secondhand.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class CategoryController {
}
