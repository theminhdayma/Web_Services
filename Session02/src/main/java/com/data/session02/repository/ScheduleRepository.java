package com.data.session02.repository;

import com.data.session02.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMovieId(Long movieId);
    List<Schedule> findByScreenRoomId(Long screenRoomId);
}
