package com.data.session06.controller;

import com.data.session06.dto.response.ApiDataResponse;
import com.data.session06.entity.Classes;
import com.data.session06.repository.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
public class ClassesController {
    private final ClassesRepository classesRepository;

    @GetMapping
    public List<Classes> getAll() {
        return classesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Classes getClass(@PathVariable long id) {
        return classesRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Classes addClass(@RequestBody Classes classes) {
        return classesRepository.save(classes);
    }

    @PutMapping("/{id}")
    public Classes updateClass(@PathVariable long id, @RequestBody Classes classes) {
        if (classesRepository.existsById(id)) {
            classes.setClassId(id);
            return classesRepository.save(classes);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Classes deleteClass(@PathVariable long id) {
        if (classesRepository.existsById(id)) {
            Classes classes = classesRepository.findById(id).orElse(null);
            classesRepository.deleteById(id);
            return classes;
        }
        return null;
    }
}
