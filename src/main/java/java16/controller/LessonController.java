package java16.controller;

import java16.entities.Lesson;
import java16.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

        @GetMapping
        public String getLessons(@RequestParam("courseId") Long courseId, Model model) {
            List<Lesson> lessons = lessonService.findAll(courseId);
            model.addAttribute("lessons", lessons);
            model.addAttribute("courseId", courseId);
            return "lessons";
        }

        @GetMapping("/new")
        public String showAddLessonForm(@RequestParam("courseId") Long courseId, Model model) {
            model.addAttribute("lesson", new Lesson());
            model.addAttribute("courseId", courseId);
            return "newLesson";
        }

        @PostMapping
        public String addLesson(@RequestParam("courseId") Long courseId, @ModelAttribute Lesson lesson) {
            lessonService.save(courseId, lesson);
            return "redirect:/courses";
        }

    @PostMapping("/delete/{id}")
    public String deleteLesson(@PathVariable("id") Long id) {
        lessonService.deleteById(id);
        return "redirect:/lessons";
    }

        @GetMapping("/edit/{id}")
        public String showEditForm(@PathVariable("id") Long id, Model model) {
            Lesson lesson = lessonService.findById(id);
            model.addAttribute("lesson", lesson);
            return "editLesson";
        }

        @PostMapping("/update/{id}")
        public String updateLesson(@PathVariable("id") Long id, @ModelAttribute Lesson lesson, @RequestParam("courseId") Long courseId) {
            lessonService.update(id, lesson);
            return "redirect:/lessons";
        }
    }

