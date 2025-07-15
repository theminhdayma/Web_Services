package com.data.session05.controller;

import com.data.session05.entity.Student;
import com.data.session05.model.dto.response.DataResponse;
import com.data.session05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(new DataResponse<>(students, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Student>> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok(new DataResponse<>(student, HttpStatus.OK)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new DataResponse<>(null, HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Student>> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(savedStudent, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Student>> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails)
                .map(student -> ResponseEntity.ok(new DataResponse<>(student, HttpStatus.OK)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new DataResponse<>(null, HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new DataResponse<>(null, HttpStatus.NO_CONTENT));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/search/name")
    public ResponseEntity<DataResponse<List<Student>>> getStudentsByName(@RequestParam String name) {
        List<Student> students = studentService.getStudentsByName(name);
        return ResponseEntity.ok(new DataResponse<>(students, HttpStatus.OK));
    }

    @GetMapping("/search/address")
    public ResponseEntity<DataResponse<List<Student>>> getStudentsByAddress(@RequestParam String address) {
        List<Student> students = studentService.getStudentsByAddress(address);
        return ResponseEntity.ok(new DataResponse<>(students, HttpStatus.OK));
    }

    @GetMapping("/search/class")
    public ResponseEntity<DataResponse<List<Student>>> getStudentsByClassName(@RequestParam String className) {
        List<Student> students = studentService.getStudentsByClassName(className);
        return ResponseEntity.ok(new DataResponse<>(students, HttpStatus.OK));
    }
}
