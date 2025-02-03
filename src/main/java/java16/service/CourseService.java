package java16.service;

import java16.entities.Course;
import java16.entities.Lesson;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    void save(Course course);
    void deleteById(Long id);
    Course findById(Long id);
    List<Lesson> findByCourseId(Long id);
    String updateCourse(Long id,Course course);
}
