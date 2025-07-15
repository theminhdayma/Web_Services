package com.data.session06.controller;

import com.data.session06.entity.Students;
import com.data.session06.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Students getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Students createStudent(Students student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public Students updateStudent(@PathVariable Long id, @RequestBody Students student) {
        if (studentRepository.existsById(id)) {
            student.setStuId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}
