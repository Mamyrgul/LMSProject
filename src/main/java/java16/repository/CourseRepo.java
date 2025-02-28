package java16.repository;

import java16.entities.Course;
import java16.entities.Instructor;
import java16.entities.Lesson;
import java16.entities.Student;

import java.util.List;

public interface CourseRepo {
    List<Course> findAll();
    void save(Course course);
    void deleteById(Long id);
    Course findById(Long id);
    List<Lesson> findByCourseId(Long id);
    String updateCourse(Long id,Course course);
    public List<Instructor> findInstructorsByCourseId(Long courseId);
    public List<Student> findStudentsByCourseId(Long courseId);
}
