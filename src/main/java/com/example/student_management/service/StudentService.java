package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public boolean deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Long countStudents() {
        return studentRepository.count();
    }

    public Collection<Object[]> countByYear() {
        return studentRepository.findNumberOfStudentByYear();
    }

    public boolean delete(int i) {
        Optional<Student> student = Optional.ofNullable(studentRepository.findById((long) i));
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }

}
