package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.AccessDeniedException;
import com.ui.ac.shop.ir.shop.Service.CategoryService;
import com.ui.ac.shop.ir.shop.model.Product.Category;
import com.ui.ac.shop.ir.shop.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> categories() {
        return categoryService.getCategory();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addCategory(@RequestBody Map<String, String> content, @RequestAttribute(name = "user") User user) {
        if (user.getIsStaff()) {
            categoryService.addCategory(new Category(content.get("name")));
            return new ResponseEntity<>(Map.of("Message" , "Category with name = " + content.get("name") + " Added." ) , HttpStatus.OK);
        }throw new AccessDeniedException();
    }
}
