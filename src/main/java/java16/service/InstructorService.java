package java16.service;

import java16.entities.Course;
import java16.entities.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> findAll();
    void save(Instructor instructor);
    void deleteById(Long id);
    Instructor findById(Long id);
    String assignInstructorToCourse(Long courseId, Long instructorId);
    String updateInstructor(Long id,Instructor instructor);
    public List<Course> findCoursesByInstructorId(Long instructorId);
}
