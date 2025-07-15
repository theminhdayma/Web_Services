package com.data.session06.service;

import com.data.session06.entity.Classes;
import com.data.session06.repository.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassesRepository classesRepository;

    public List<Classes> findAll() {
        return classesRepository.findAll();
    }

    public Classes findById(Long id) {
        return classesRepository.findById(id).orElse(null);
    }

    public Classes save(Classes classes) {
        return classesRepository.save(classes);
    }

    public Classes update(Long id, Classes classes) {
        if (classesRepository.existsById(id)) {
            classes.setClassId(id);
            return classesRepository.save(classes);
        }
        return null;
    }

    public void deleteById(Long id) {
        classesRepository.deleteById(id);
    }
}
