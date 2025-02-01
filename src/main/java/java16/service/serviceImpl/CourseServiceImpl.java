package java16.service.serviceImpl;

import java16.entities.Course;
import java16.entities.Lesson;
import java16.repository.CourseRepo;
import java16.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl  implements CourseService {
    private final CourseRepo courseRepo;
    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public void save(Course course) {
    courseRepo.save(course);
    }

    @Override
    public void deleteById(Long id) {
   courseRepo.deleteById(id);
    }

    @Override
    public Course findById(Long id) {
        return courseRepo.findById(id);
    }

    @Override
    public List<Lesson> findByCourseId(Long id) {
        return courseRepo.findByCourseId(id);
    }
}
