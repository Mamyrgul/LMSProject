package java16.controller;

import java16.entities.Course;
import java16.entities.Instructor;
import java16.entities.Student;
import java16.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/assign")
    public String showAssignStudentForm() {
        return "assign-student";
    }

    @PostMapping("/assign")
    public String assignStudent(@RequestParam("courseId") Long courseId,
                                @RequestParam("studentId") Long studentId) {
        studentService.assignToCourse(courseId, studentId);
        return "redirect:/students";
    }

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "newStudent";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "editStudent";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id,
                                @ModelAttribute Student student) {
        studentService.update(id, student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/courses")
    public String getCoursesByStudent(@PathVariable("id") Long studentId, Model model) {
        List<Course> courses = studentService.findCoursesByStudentId(studentId);
        model.addAttribute("courses", courses);
        return "coursesByStudent";
    }
}


