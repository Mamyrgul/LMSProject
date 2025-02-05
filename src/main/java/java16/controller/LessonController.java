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
    @GetMapping("/edit/{courseId}/{id}")
    public String showEditForm(@PathVariable("courseId") Long courseId, @PathVariable("id") Long lessonId, Model model) {
        Lesson lesson = lessonService.findById(lessonId);
        model.addAttribute("lesson", lesson);
        model.addAttribute("courseId", courseId);
        return "editLesson";
    }

    @PostMapping("/update/{courseId}/{id}")
    public String updateLesson(
            @PathVariable("courseId") Long courseId,
            @PathVariable("id") Long lessonId,
            @ModelAttribute Lesson updatedLesson) {
        lessonService.update(courseId, lessonId, updatedLesson);
        return "redirect:/lessons?courseId=" + courseId;
    }


    // Метод для удаления урока
    @PostMapping("/delete/{courseId}/{lessonId}")
    public String deleteLesson(@PathVariable("courseId") Long courseId, @PathVariable("lessonId") Long lessonId) {
        lessonService.deleteById(courseId, lessonId);
        return "redirect:/courses";
    }
    }

