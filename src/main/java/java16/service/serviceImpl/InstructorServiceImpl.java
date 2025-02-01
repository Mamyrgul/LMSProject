package java16.service.serviceImpl;

import java16.entities.Instructor;
import java16.repository.InstructorRepo;
import java16.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;

    @Override
    public List<Instructor> findAll() {
        return instructorRepo.findAll();
    }

    @Override
    public void save(Instructor instructor) {
     instructorRepo.save(instructor);
    }

    @Override
    public void deleteById(Long id) {
   instructorRepo.deleteById(id);
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepo.findById(id);
    }

    @Override
    public String assignInstructorToCourse(Long courseId, Long instructorId) {
        return instructorRepo.assignInstructorToCourse(courseId, instructorId);
    }

    @Override
    public String updateInstructor(Long id, Instructor instructor) {
        return instructorRepo.updateInstructor(id, instructor);
    }
}
