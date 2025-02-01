package java16.repository.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java16.entities.Course;
import java16.entities.Instructor;
import java16.repository.InstructorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class InstructorRepoImpl implements InstructorRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public String assignInstructorToCourse(Long courseId, Long instructorId) {
        Course course = entityManager.find(Course.class, courseId);
        Instructor instructor = entityManager.find(Instructor.class, instructorId);

        course.getInstructors().add(instructor);
        instructor.getCourses().add(course);

        entityManager.merge(course);
        return "success";
    }
    @Override
    public String updateInstructor(Long id, Instructor updatedInstructor) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        instructor.setFirstName(updatedInstructor.getFirstName());
        instructor.setLastName(updatedInstructor.getLastName());
        instructor.setEmail(updatedInstructor.getEmail());
        entityManager.merge(instructor);
        return "success";
    }

    @Override
    public List<Instructor> findAll() {
        List<Instructor> instructors = entityManager.createQuery("from Instructor", Instructor.class).getResultList();
        return instructors;
    }
    @Override
    public void save(Instructor instructor) {
        if (instructor.getId() == null) {
            entityManager.persist(instructor);
        } else {
            entityManager.merge(instructor);
        }
    }


    @Override
    public void deleteById(Long id) {
    Instructor instructor = entityManager.find(Instructor.class, id);
    entityManager.remove(instructor);
        System.out.println("successfully deleted");
    }

    @Override
    public Instructor findById(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        return instructor;
    }
}
