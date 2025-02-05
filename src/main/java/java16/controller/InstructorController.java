package java16.controller;

import java16.entities.Course;
import java16.entities.Instructor;
import java16.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;
    @GetMapping("/assign")
    public String showAssignInstructorForm() {
        return "assign-instructor";
    }

    @PostMapping("/assign")
    public String assignInstructor(@RequestParam("instructorId") Long instructorId,
                                   @RequestParam("courseId") Long courseId) {
        instructorService.assignInstructorToCourse(instructorId, courseId);
        return "redirect:/instructors";
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("instructors", instructorService.findAll());
        return "instructors";
    }
    @GetMapping("/new")
    public String newInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "newInstructor";
    }

    @PostMapping
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor) {
        instructorService.save(instructor);
        return "redirect:/instructors";
    }

   @GetMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable("id") Long id) {
        instructorService.deleteById(id);
        return "redirect:/instructors";
   }

    @GetMapping("/edit/{id}")
    public String editInstructor(@PathVariable("id") Long id, Model model) {
       model.addAttribute("instructor", instructorService.findById(id));
        return "editInstructor";
    }
    @PostMapping("/update/{id}")
    public String updateInstructor(
            @PathVariable("id") Long id,
            @ModelAttribute Instructor updatedInstructor) {
        instructorService.updateInstructor(id, updatedInstructor);
        return "redirect:/instructors";
    }

    @GetMapping("/{id}/courses")
    public String getCoursesByInstructor(@PathVariable("id") Long instructorId, Model model) {
        List<Course> courses = instructorService.findCoursesByInstructorId(instructorId);
        model.addAttribute("courses", courses);
        return "coursesByInstructor";
    }

}
