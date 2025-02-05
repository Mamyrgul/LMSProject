package java16.repository.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java16.entities.Course;
import java16.entities.Student;
import java16.repository.StudentRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public String assignToCourse(Long courseId, Long studentId) {
        Course course = entityManager.find(Course.class, courseId);
        Student student = entityManager.find(Student.class, studentId);

        course.getStudents().add(student);
        student.getCourses().add(course);

        entityManager.merge(course);
        return "success";
    }

    @Override
    public String update(Long id, Student updatedStudent) {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }
        student.setFirstName(updatedStudent.getFirstName());
        student.setEmail(updatedStudent.getEmail());
        student.setPhoneNumber(updatedStudent.getPhoneNumber());
        entityManager.merge(student);
        return "success";
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = entityManager.createQuery("from Student", Student.class).getResultList();
        return students;
    }

    @Override
    public void save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
    }

    @Override
    public void deleteById(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            for (Course course : student.getCourses()) {
                course.getStudents().remove(student);
            }
            entityManager.remove(student);
        }
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Course> findCoursesByStudentId(Long studentId) {
        Student student = entityManager.find(Student.class, studentId);
        return student != null ? student.getCourses() : new ArrayList<>();
    }
}
