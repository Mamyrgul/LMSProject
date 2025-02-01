package java16.service;

import java16.entities.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> findAll(Long courseId);
    void save(Long courseId,Lesson lesson);
    void deleteById(Long id);
    Lesson findById(Long id);
    String update(Long id, Lesson lesson);
}
