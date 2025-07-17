package com.data.session08.controller;

import com.data.session08.entity.ApiResponse;
import com.data.session08.entity.Ingredient;
import com.data.session08.service.CloudinaryService;
import com.data.session08.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public ApiResponse<Ingredient> create(
            @RequestParam String name,
            @RequestParam Integer stock,
            @RequestParam String expiry,
            @RequestParam MultipartFile image
    ) {
        try {
            String url = cloudinaryService.upload(image);
            Ingredient ingredient = new Ingredient();
            ingredient.setName(name);
            ingredient.setStock(stock);
            ingredient.setExpiry(LocalDate.parse(expiry));
            ingredient.setImage(url);
            return new ApiResponse<>(true, "Ingredient created", ingredientService.save(ingredient));
        } catch (Exception e) {
            return new ApiResponse<>(false, "Error: " + e.getMessage(), null);
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Ingredient> update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Integer stock,
            @RequestParam String expiry,
            @RequestParam(required = false) MultipartFile image
    ) {
        try {
            Ingredient existing = ingredientService.findById(id);
            existing.setName(name);
            existing.setStock(stock);
            existing.setExpiry(LocalDate.parse(expiry));

            if (image != null && !image.isEmpty()) {
                String url = cloudinaryService.upload(image);
                existing.setImage(url);
            }

            return new ApiResponse<>(true, "Ingredient updated", ingredientService.save(existing));
        } catch (Exception e) {
            return new ApiResponse<>(false, "Error: " + e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        try {
            ingredientService.delete(id);
            return new ApiResponse<>(true, "Ingredient deleted", null);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Error: " + e.getMessage(), null);
        }
    }

    @GetMapping
    public ApiResponse<List<Ingredient>> getAll() {
        return new ApiResponse<>(true, "Ingredient list", ingredientService.findAll());
    }
}
