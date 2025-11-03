package com.example.student_management.repository;

import com.example.student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findById(long id);

    @Query("SELECT YEAR(s.DateOfBirth) AS year, COUNT(s) AS count FROM Student s " +
           "GROUP BY YEAR(s.DateOfBirth)")
    Collection<Object[]> findNumberOfStudentByYear();
}
