package com.pet.sms.controller;

import com.pet.sms.entity.Student;
import com.pet.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    /**
     * Handler method to handle list students and return model and view
     * @param model
     * @return model and view
     */
    @GetMapping("students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudent());
        return "students";
    }
}
