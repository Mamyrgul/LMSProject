package java16.controller;

import java16.entities.Course;
import java16.entities.Instructor;
import java16.entities.Student;
import java16.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

        @GetMapping
        public String getAllCourses(Model model) {
            List<Course> courses = courseService.findAll();
            model.addAttribute("courses", courses);
            return "courses";
        }

        @GetMapping("/new")
        public String newCourse(Model model) {
            model.addAttribute("course", new Course());
            return "newCourse";
        }

        @PostMapping
        public String saveCourse(@ModelAttribute("course") Course course) {
            courseService.save(course);
            return "redirect:/courses";
        }

        @GetMapping("/edit/{id}")
        public String editCourse(@PathVariable("id") Long id, Model model) {
            Course course = courseService.findById(id);
            model.addAttribute("course", course);
            return "editCourse";
        }
    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute Course course) {
        course.setId(id);
        courseService.updateCourse(id, course);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
        public String deleteCourse(@PathVariable("id") Long id) {
            courseService.deleteById(id);
            return "redirect:/courses";
        }
    @GetMapping("/{id}/students")
    public String getStudentsByCourse(@PathVariable("id") Long courseId, Model model) {
        List<Student> students = courseService.findStudentsByCourseId(courseId);
        model.addAttribute("students", students);
        return "studentsByCourse";
    }
    @GetMapping("/{id}/instructors")
    public String getInstructorsByCourse(@PathVariable("id") Long courseId, Model model) {
        List<Instructor> instructors = courseService.findInstructorsByCourseId(courseId);
        model.addAttribute("instructors", instructors);
        return "instructorsByCourse";
    }

}

