package java16.service.serviceImpl;

import java16.entities.Lesson;
import java16.repository.LessonRepo;
import java16.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepo lessonRepo;

    @Override
    public List<Lesson> findAll(Long courseId) {
        return lessonRepo.findAll(courseId);
    }

    @Override
    public void save(Long courseId, Lesson lesson) {
         lessonRepo.save(courseId, lesson);
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepo.findById(id);
    }

    @Override
    public void deleteById(Long courseId, Long LessonId) {
        lessonRepo.deleteById(courseId, LessonId);
    }

    @Override
    public void update(Long courseId, Long lessonId, Lesson updatedLesson) {
        lessonRepo.update(courseId, lessonId, updatedLesson);
    }
}
