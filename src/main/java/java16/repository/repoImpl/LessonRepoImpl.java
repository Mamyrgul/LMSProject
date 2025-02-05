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
    public void deleteById(Long courseId, Long LessonId) {
        Course course = entityManager.find(Course.class, courseId);
        Lesson lesson = entityManager.find(Lesson.class, LessonId);

        course.getLessons().remove(lesson);
        entityManager.remove(lesson);
        System.out.println("successfully deleted lesson");
    }

    @Override
    public Lesson findById(Long lessonId) {
        return entityManager.find(Lesson.class, lessonId);
    }

    @Override
    public void update(Long courseId, Long lessonId, Lesson updatedLesson) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        if (lesson != null && lesson.getCourse().getId().equals(courseId)) {
            lesson.setTitle(updatedLesson.getTitle());
            lesson.setDescription(updatedLesson.getDescription());
            lesson.setDateOfStart(updatedLesson.getDateOfStart());
            entityManager.merge(lesson);
        }
    }

}
