package com.data.session06.service;

import com.data.session06.entity.Students;
import com.data.session06.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Students> findAll() {
        return studentRepository.findAll();
    }

    public Students findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Students save(Students student) {
        return studentRepository.save(student);
    }

    public Students update(Long id, Students student) {
        if (studentRepository.existsById(id)) {
            student.setStuId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
