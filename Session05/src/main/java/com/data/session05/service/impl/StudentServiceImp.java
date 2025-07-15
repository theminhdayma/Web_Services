package com.data.session05.service.impl;

import com.data.session05.entity.Student;
import com.data.session05.repository.StudentRepository;
import com.data.session05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setFullName(studentDetails.getFullName());
            student.setGender(studentDetails.getGender());
            student.setBirthday(studentDetails.getBirthday());
            student.setAddress(studentDetails.getAddress());
            student.setClassName(studentDetails.getClassName());
            return studentRepository.save(student);
        });
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentRepository.findById(id).map(student -> {
            studentRepository.delete(student);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByFullNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> getStudentsByAddress(String address) {
        return studentRepository.findByAddressContainingIgnoreCase(address);
    }

    @Override
    public List<Student> getStudentsByClassName(String className) {
        return studentRepository.findByClassName(className);
    }
}
