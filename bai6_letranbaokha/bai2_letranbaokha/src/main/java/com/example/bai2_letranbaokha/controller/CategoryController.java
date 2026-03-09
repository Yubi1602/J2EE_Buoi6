package com.example.bai2_letranbaokha.controller;


import com.example.bai2_letranbaokha.model.Category;
import com.example.bai2_letranbaokha.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category/list";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }
}