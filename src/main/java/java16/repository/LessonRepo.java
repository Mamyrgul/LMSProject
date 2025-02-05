package java16.repository;

import java16.entities.Lesson;
import java16.entities.Student;

import java.util.List;

public interface LessonRepo {
    List<Lesson> findAll(Long courseId);
    Lesson findById(Long lessonId);
    void save(Long courseId, Lesson lesson);
    void update(Long courseId, Long lessonId, Lesson updatedLesson);
    void deleteById(Long courseId, Long lessonId);
}
