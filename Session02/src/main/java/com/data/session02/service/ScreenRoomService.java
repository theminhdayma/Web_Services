package com.data.session02.service;

import com.data.session02.entity.ScreenRoom;
import com.data.session02.repository.ScreenRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScreenRoomService implements AppService<ScreenRoom, Long> {

    private final ScreenRoomRepository screenRoomRepository;

    @Override
    public ScreenRoom save(ScreenRoom entity) {
        return screenRoomRepository.save(entity);
    }

    @Override
    public Optional<ScreenRoom> findById(Long id) {
        return screenRoomRepository.findById(id);
    }

    @Override
    public ScreenRoom update(ScreenRoom entity) {
        return screenRoomRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        screenRoomRepository.deleteById(id);
    }

    public List<ScreenRoom> getAllScreenRooms() {
        return screenRoomRepository.findAll();
    }

}
