package com.data.session04.service;

import com.data.session04.entity.FoodItem;
import com.data.session04.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodItemService implements AppService<FoodItem, Long> {

    private final FoodItemRepository repository;

    public Page<FoodItem> getAll(String name, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return repository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name, category, pageable);
    }


    @Override
    public FoodItem save(FoodItem item) {
        return repository.save(item);
    }


    @Override
    public Optional<FoodItem> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public FoodItem update(FoodItem item) {
        if (item.getId() != null && repository.existsById(item.getId())) {
            return repository.save(item);
        }
        throw new RuntimeException("Không tìm thấy thực phẩm để cập nhật");
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    public void deleteById(Long id) {
        delete(id);
    }
}
