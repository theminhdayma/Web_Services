package com.data.session04.service;

import com.data.session04.entity.Flight;
import com.data.session04.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Page<Flight> searchFlights(String from, String to, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return flightRepository.findByDepartureContainingIgnoreCaseAndDestinationContainingIgnoreCase(from, to, pageable);
    }

    public Flight findById(Integer id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void saveAll(Iterable<Flight> flights) {
        flightRepository.saveAll(flights);
    }
}
