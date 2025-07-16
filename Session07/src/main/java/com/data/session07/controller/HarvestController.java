package com.data.session07.controller;

import com.data.session07.entity.Harvest;
import com.data.session07.repository.HarvestRepository;
import com.data.session07.service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/harvests")
public class HarvestController {

    private final HarvestService harvestService;
    @GetMapping
    public List<Harvest> getAllHarvests() {
        return harvestService.findAll();
    }

    @GetMapping("/{id}")
    public Harvest getHarvestById(@PathVariable Long id) {
        return harvestService.findById(id);
    }

    @PostMapping
    public Harvest createHarvest(@RequestBody Harvest harvest) {
        return harvestService.save(harvest);
    }

    @PutMapping("/{id}")
    public void updateHarvest(@PathVariable Long id, @RequestBody Harvest harvest) {
        Harvest existingHarvest = harvestService.findById(id);
        if (existingHarvest == null) {
            throw new RuntimeException("Harvest not found with id: " + id);
        }
        harvest.setId(id);
        harvestService.update(harvest);
    }

    @DeleteMapping("/{id}")
    public void deleteHarvest(@PathVariable Long id) {
        Harvest existingHarvest = harvestService.findById(id);
        if (existingHarvest == null) {
            throw new RuntimeException("Harvest not found with id: " + id);
        }
        harvestService.deleteById(id);
    }
}
