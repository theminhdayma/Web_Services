package com.data.session07.service;

import com.data.session07.entity.Seed;
import com.data.session07.repository.SeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeedService {

    private final SeedRepository seedRepository;

    public List<Seed> findAll() {
        return seedRepository.findAll();
    }

    public Seed findById(Long id) {
        return seedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seed not found with id: " + id));
    }

    public Seed save(Seed seed) {
        return seedRepository.save(seed);
    }

    public void deleteById(Long id) {
        if (!seedRepository.existsById(id)) {
            throw new RuntimeException("Seed not found with id: " + id);
        }
        seedRepository.deleteById(id);
    }

    public void update(Seed seed) {
        if (!seedRepository.existsById(seed.getId())) {
            throw new RuntimeException("Seed not found with id: " + seed.getId());
        }
        seedRepository.save(seed);
    }
}
