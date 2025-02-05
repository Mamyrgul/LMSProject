package java16.repository;

import java16.entities.Course;
import java16.entities.Instructor;
import java16.entities.Student;

import java.util.List;

public interface InstructorRepo {
    List<Instructor> findAll();
    void save(Instructor instructor);
    void deleteById(Long id);
    Instructor findById(Long id);
    String assignInstructorToCourse(Long courseId, Long instructorId);
    String updateInstructor(Long id,Instructor instructor);
    List<Course> findCoursesByInstructorId(Long instructorId);

}
