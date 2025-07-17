package com.data.session08.service;

import com.data.session08.entity.Ingredient;
import com.data.session08.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Long id, Ingredient updatedIngredient) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
        existing.setName(updatedIngredient.getName());
        existing.setStock(updatedIngredient.getStock());
        existing.setExpiry(updatedIngredient.getExpiry());
        existing.setImage(updatedIngredient.getImage());
        return ingredientRepository.save(existing);
    }

    public void delete(Long id) {
        if (!ingredientRepository.existsById(id))
            throw new EntityNotFoundException("Ingredient not found");
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
    }
}
