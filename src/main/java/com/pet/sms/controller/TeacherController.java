package com.pet.sms.controller;

import com.pet.sms.entity.Teacher;
import com.pet.sms.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        super();
        this.teacherService = teacherService;
    }

    /**
     * Handler method to handle list teachers and return model and view
     * @param model
     * @return model and view
     */
    @GetMapping("teachers")
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeacher());

        return "teachers";
    }

    @GetMapping("teachers/new")
    public String createTeacherForm(Model model) {
        // create teacher object to hold teacher form data
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);

        return "create_teacher";
    }

    @PostMapping("teachers")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);

        return "redirect:/teachers";
    }

    @GetMapping("teachers/edit/{id}")
    public String editTeacherForm(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "edit_teacher";
    }

    @PostMapping("teachers/{id}")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute("teacher") Teacher teacher, Model model) {
        // get teacher from DB by id
        Teacher existingTeacher = teacherService.getTeacherById(id);
        if (existingTeacher == null) return "redirect:/teachers";
        existingTeacher.setId(id);
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setEmail(teacher.getEmail());

        // save updated teacher object
        teacherService.updateTeacher(existingTeacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers";
    }
}
