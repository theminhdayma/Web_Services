package com.data.session07.service;

import com.data.session07.entity.Harvest;
import com.data.session07.repository.HarvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HarvestService {
    private final HarvestRepository harvestRepository;

    public List<Harvest> findAll() {
        return harvestRepository.findAll();
    }

    public Harvest findById(Long id) {
        return harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest not found with id: " + id));
    }

    public Harvest save(Harvest harvest) {
        return harvestRepository.save(harvest);
    }

    public void deleteById(Long id) {
        if (!harvestRepository.existsById(id)) {
            throw new RuntimeException("Harvest not found with id: " + id);
        }
        harvestRepository.deleteById(id);
    }

    public void update(Harvest harvest) {
        if (!harvestRepository.existsById(harvest.getId())) {
            throw new RuntimeException("Harvest not found with id: " + harvest.getId());
        }
        harvestRepository.save(harvest);
    }
}
