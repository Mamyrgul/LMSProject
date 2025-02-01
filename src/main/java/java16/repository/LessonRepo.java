package java16.repository;

import java16.entities.Lesson;
import java16.entities.Student;

import java.util.List;

public interface LessonRepo {
    List<Lesson> findAll(Long courseId);
    void save(Long courseId,Lesson lesson);
    void deleteById(Long id);
    Lesson findById(Long id);
    String update(Long id, Lesson lesson);
}
