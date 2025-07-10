package com.data.session02.service;

import com.data.session02.entity.Seat;
import com.data.session02.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService implements AppService<Seat, Long> {

    private final SeatRepository seatRepository;

    @Override
    public Seat save(Seat entity) {
        return seatRepository.save(entity);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat update(Seat entity) {
        return seatRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        seatRepository.deleteById(id);
    }
}
