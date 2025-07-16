package com.data.session07.controller;

import com.data.session07.entity.Worker;
import com.data.session07.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.findAll();
    }

    @GetMapping("/{id}")
    public Worker getWorkerById(Long id) {
        return workerService.findById(id);
    }

    @PostMapping
    public Worker createWorker(Worker worker) {
        return workerService.save(worker);
    }

    @PutMapping("/{id}")
    public void updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        Worker existingWorker = workerService.findById(id);
        if (existingWorker == null) {
            throw new RuntimeException("Worker not found with id: " + id);
        }
        worker.setId(id);
        workerService.update(worker);
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id) {
        Worker existingWorker = workerService.findById(id);
        if (existingWorker == null) {
            throw new RuntimeException("Worker not found with id: " + id);
        }
        workerService.deleteById(id);
    }
}
