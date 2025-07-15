package com.data.session05.service;


import com.data.session05.entity.Fruit;
import com.data.session05.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }

    public Fruit getFruitById(Long id) {
        return fruitRepository.findById(id).orElse(null);
    }

    public Fruit createFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public Fruit updateFruit(Long id, Fruit fruitData) {
        Optional<Fruit> optional = fruitRepository.findById(id);
        if (optional.isPresent()) {
            Fruit fruit = optional.get();
            fruit.setName(fruitData.getName());
            fruit.setPrice(fruitData.getPrice());
            fruit.setStock(fruitData.getStock());
            fruit.setStatus(fruitData.getStatus());
            fruit.setCreatedAt(fruitData.getCreatedAt());
            return fruitRepository.save(fruit);
        }
        return null;
    }

    public void deleteFruit(Long id) {
        fruitRepository.deleteById(id);
    }
}
