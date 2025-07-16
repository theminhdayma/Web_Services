package com.data.session07.service;

import com.data.session07.entity.Worker;
import com.data.session07.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found with id: " + id));
    }

    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    public void deleteById(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new RuntimeException("Worker not found with id: " + id);
        }
        workerRepository.deleteById(id);
    }

    public void update(Worker worker) {
        if (!workerRepository.existsById(worker.getId())) {
            throw new RuntimeException("Worker not found with id: " + worker.getId());
        }
        workerRepository.save(worker);
    }
}
