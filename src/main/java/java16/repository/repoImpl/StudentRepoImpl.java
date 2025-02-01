package java16.repository.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java16.entities.Course;
import java16.entities.Student;
import java16.repository.StudentRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class StudentRepoImpl implements StudentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    public StudentRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = entityManager.createQuery("from Student", Student.class).getResultList();
        return students;
    }

    @Override
    public void save(Student student) {
    entityManager.persist(student);
        System.out.println(student);
    }

    @Override
    public void deleteById(Long id) {
    Student student = entityManager.find(Student.class, id);
    entityManager.remove(student);
        System.out.println("student deleted");
    }

    @Override
    public Student findById(Long id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    @Override
    public String update(Long id, Student student) {
        student = entityManager.find(Student.class, id);
        entityManager.merge(student);
        return "student updated";
    }

    @Override
    public String assignToCourse(Long courseId, Long studentId) {
        Course course = entityManager.find(Course.class, courseId);
        Student student = entityManager.find(Student.class, studentId);
        student.getCourses().add(course);
        course.getStudents().add(student);
        entityManager.merge(course);
        return "course assigned";
    }
}
