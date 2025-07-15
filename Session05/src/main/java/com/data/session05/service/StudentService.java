package com.data.session05.service;

import com.data.session05.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student createStudent(Student student);
    Optional<Student> updateStudent(Long id, Student student);
    boolean deleteStudent(Long id);
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByAddress(String address);
    List<Student> getStudentsByClassName(String className);
}

