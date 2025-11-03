package com.example.student_management;

import com.example.student_management.controller.StudentController;
import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private
    StudentController studentController;

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setLastName("Mido");

        when(studentService.save(any(Student.class))).thenReturn(student);

        ResponseEntity<Student> response = studentController.save(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Mido", response.getBody().getLastName());
    }

    @Test
    void testDeleteStudent() {
        when(studentService.deleteStudent(1L)).thenReturn(true);
        ResponseEntity<Void> response = studentController.deleteStudent(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testFindAllStudents() {
        Student student1 = new Student();
        Student student2 = new Student();
        when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));

        ResponseEntity<List<Student>> response = studentController.findAll();

        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCountStudents() {
        when(studentService.countStudents()).thenReturn(10L);
        ResponseEntity<Long> response = studentController.countStudents();
        assertEquals(10L, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFindByYear() {
        when(studentService.countByYear()).thenReturn(Arrays.asList());
        ResponseEntity<Collection<?>> response = studentController.countByYear();
        assertEquals(0, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
