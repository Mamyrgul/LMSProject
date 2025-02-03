package java16.controller;

import java16.service.CourseService;
import java16.service.InstructorService;
import java16.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final InstructorService instructorService;

    public DashboardController(CourseService courseService, StudentService studentService, InstructorService instructorService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.instructorService = instructorService;
    }

    @GetMapping
    public String showDashboard(Model model) {
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("instructors", instructorService.findAll());
        return "dashboard";
    }
}

