package java16.service;

import java16.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    void save(Student student);
    void deleteById(Long id);
    Student findById(Long id);
    String update(Long id,Student student);
    String assignToCourse(Long courseId, Long studentId);
}
