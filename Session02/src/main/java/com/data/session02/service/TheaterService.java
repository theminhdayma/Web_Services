package com.data.session02.service;

import com.data.session02.entity.Theater;
import com.data.session02.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TheaterService implements AppService<Theater, Long> {

    private final TheaterRepository theaterRepository;

    @Override
    public Theater save(Theater entity) {
        return theaterRepository.save(entity);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater update(Theater entity) {
        return theaterRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        theaterRepository.deleteById(id);
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }
}
