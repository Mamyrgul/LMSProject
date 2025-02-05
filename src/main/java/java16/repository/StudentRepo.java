package java16.repository;

import java16.entities.Course;
import java16.entities.Student;

import java.util.List;

public interface StudentRepo {
    List<Student> findAll();
    void save(Student student);
    void deleteById(Long id);
    Student findById(Long id);
    String update(Long id,Student student);
    String assignToCourse(Long courseId, Long studentId);
    List<Course> findCoursesByStudentId(Long studentId);
}
