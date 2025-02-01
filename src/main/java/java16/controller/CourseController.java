package java16.controller;

import java16.entities.Course;
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
        public String saveCourse(@ModelAttribute Course course) {
            courseService.save(course);
            return "redirect:/courses";
        }

        @GetMapping("/edit/{id}")
        public String editCourse(@PathVariable("id") Long id, Model model) {
            Course course = courseService.findById(id);
            model.addAttribute("course", course);
            return "editCourse";
        }

        @GetMapping("/delete/{id}")
        public String deleteCourse(@PathVariable("id") Long id) {
            courseService.deleteById(id);
            return "redirect:/courses";
        }
    }

