package java16.repository.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java16.entities.Course;
import java16.entities.Instructor;
import java16.entities.Lesson;
import java16.entities.Student;
import java16.repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CourseRepoImpl implements CourseRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("from Course", Course.class).getResultList();
    }

    @Override
    public void save(Course course) {
    entityManager.persist(course);
        System.out.println(course);
    }

    @Override
    public void deleteById(Long id) {
        Course course = entityManager.find(Course.class, id);

        for (Instructor instructor : course.getInstructors()) {
            instructor.getCourses().remove(course);
        }

        for (Student student : course.getStudents()) {
            student.getCourses().remove(course);
        }

        entityManager.remove(course);
        System.out.println("Successfully deleted course");
    }


    @Override
    public Course findById(Long id) {
        Course course = entityManager.find(Course.class, id);
        return course;
    }

    @Override
    public List<Lesson> findByCourseId(Long id) {
        Course course = entityManager.find(Course.class, id);
        return course.getLessons();
    }

    @Override
    public String updateCourse(Long id, Course course) {
        course = entityManager.find(Course.class, id);
        entityManager.merge(course);
        return "successfully updated course";
    }
    public List<Instructor> findInstructorsByCourseId(Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        return course.getInstructors();
    }
    public List<Student> findStudentsByCourseId(Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        return course.getStudents();
    }


}
