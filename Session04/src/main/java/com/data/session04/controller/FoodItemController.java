package com.data.session04.controller;

import com.data.session04.entity.FoodItem;
import com.data.session04.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodService;

    @GetMapping
    public String listFoods(Model model,
                            @RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "") String category,
                            @RequestParam(defaultValue = "0") int page) {
        Page<FoodItem> foodPage = foodService.getAll(name, category, page, 5);
        model.addAttribute("foodPage", foodPage);
        model.addAttribute("name", name);
        model.addAttribute("category", category);
        return "food/foodList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        return "food/foodForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute FoodItem foodItem) {
        foodService.save(foodItem);
        return "redirect:/foods";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        FoodItem item = foodService.findById(id).orElse(null);
        if (item == null) return "redirect:/foods";
        model.addAttribute("foodItem", item);
        return "food/foodForm";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute FoodItem foodItem) {
        foodService.save(foodItem);
        return "redirect:/foods";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        foodService.deleteById(id);
        return "redirect:/foods";
    }
}
