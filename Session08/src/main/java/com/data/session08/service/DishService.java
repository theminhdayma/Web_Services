package com.data.session08.service;

import com.data.session08.dto.DishDto;
import com.data.session08.entity.Dish;
import com.data.session08.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Dish createDish(DishDto dto) throws IOException {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setStatus(dto.getStatus());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String imageUrl = cloudinaryService.upload(dto.getImage());
            dish.setImage(imageUrl);
        }

        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, DishDto dto) throws IOException {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Dish not found"));

        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setStatus(dto.getStatus());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String imageUrl = cloudinaryService.upload(dto.getImage());
            dish.setImage(imageUrl);
        }

        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new NoSuchElementException("Dish not found");
        }
        dishRepository.deleteById(id);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
}
