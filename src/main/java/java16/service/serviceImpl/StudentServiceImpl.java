package java16.service.serviceImpl;

import java16.entities.Student;
import java16.repository.StudentRepo;
import java16.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public void save(Student student) {
    studentRepo.save(student);
    }

    @Override
    public void deleteById(Long id) {
    studentRepo.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public String update(Long id, Student student) {
        return studentRepo.update(id, student);
    }

    @Override
    public String assignToCourse(Long courseId, Long studentId) {
        return studentRepo.assignToCourse(courseId, studentId);
    }
}

