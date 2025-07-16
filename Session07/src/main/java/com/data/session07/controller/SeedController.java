package com.data.session07.controller;

import com.data.session07.entity.Seed;
import com.data.session07.repository.SeedRepository;
import com.data.session07.service.SeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seeds")
public class SeedController {

    private final SeedService seedService;

    @GetMapping
    public List<Seed> getAllSeeds() {
        return seedService.findAll();
    }

    @GetMapping("/{id}")
    public Seed getSeedById(@PathVariable Long id) {
        return seedService.findById(id);
    }

    @PostMapping
    public Seed createSeed(@RequestBody Seed seed) {
        return seedService.save(seed);
    }

    @PutMapping("/{id}")
    public void updateSeed(@PathVariable Long id, @RequestBody Seed seed) {
        Seed existingSeed = seedService.findById(id);
        if (existingSeed == null) {
            throw new RuntimeException("Seed not found with id: " + id);
        }
        seed.setId(id);
        seedService.update(seed);
    }

    @DeleteMapping("/{id}")
    public void deleteSeed(@PathVariable Long id) {
        Seed existingSeed = seedService.findById(id);
        if (existingSeed == null) {
            throw new RuntimeException("Seed not found with id: " + id);
        }
        seedService.deleteById(id);
    }
}
