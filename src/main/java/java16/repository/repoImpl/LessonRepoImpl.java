package java16.repository.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java16.entities.Course;
import java16.entities.Lesson;
import java16.repository.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class LessonRepoImpl implements LessonRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Lesson> findAll(Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        return course.getLessons();
    }

    @Override
    public void save(Long courseId, Lesson lesson) {
        Course course = entityManager.find(Course.class, courseId);
        course.getLessons().add(lesson);
        lesson.setCourse(course);
        entityManager.persist(lesson);
        System.out.println(lesson);
    }

    @Override
    public void deleteById(Long id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        entityManager.remove(lesson);
        System.out.println("Successfully deleted lesson " + id);
    }

    @Override
    public Lesson findById(Long id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        return lesson;
    }

    @Override
    public String update(Long id, Lesson lesson) {
        lesson = entityManager.find(Lesson.class, id);
        entityManager.merge(lesson);
        return "Successfully updated lesson " + id;
    }
}
