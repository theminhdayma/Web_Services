package com.data.session02.service;

import com.data.session02.entity.Schedule;
import com.data.session02.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService implements AppService<Schedule, Long> {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule entity) {
        return scheduleRepository.save(entity);
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public Schedule update(Schedule entity) {
        return scheduleRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> filterSchedules(Long movieId, LocalDate date, Long theaterId, Long screenRoomId) {
        List<Schedule> all = scheduleRepository.findAll();

        return all.stream()
                .filter(s -> movieId == null || s.getMovie().getId().equals(movieId))
                .filter(s -> date == null || s.getStartTime().toLocalDate().equals(date))
                .filter(s -> theaterId == null || s.getScreenRoom().getTheater().getId().equals(theaterId))
                .filter(s -> screenRoomId == null || s.getScreenRoom().getId().equals(screenRoomId))
                .collect(Collectors.toList());
    }

}
