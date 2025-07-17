package com.data.session08.controller;

import com.data.session08.dto.DishDto;
import com.data.session08.entity.ApiResponse;
import com.data.session08.entity.Dish;
import com.data.session08.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<ApiResponse<Dish>> create(@ModelAttribute DishDto dto) throws IOException {
        Dish dish = dishService.createDish(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish created successfully", dish));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Dish>> update(@PathVariable Long id, @ModelAttribute DishDto dto) throws IOException {
        Dish dish = dishService.updateDish(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish updated successfully", dish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish deleted successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Dish>>> getAll() {
        List<Dish> list = dishService.getAllDishes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched successfully", list));
    }
}
