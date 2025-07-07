package com.my131.edu_system.controller;

import com.my131.edu_system.model.Student;
import com.my131.edu_system.model.Teacher;
import com.my131.edu_system.repository.StudentRepository;
import com.my131.edu_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;  //Teacher 데이터도 사용해야 하기 때문에 (id : foreign key)

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentRepository.findAll());

        return "student-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("student", new Student());
        model.addAttribute("teachers", teachers);

        return "student-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Student student) {
        studentRepository.save(student);

        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Student student = studentRepository.findById(id);
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("student", student);
        model.addAttribute("teachers", teachers);

        return "student-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Student student) {
        studentRepository.update(student);

        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String deleteform(@PathVariable int id) {
        studentRepository.delete(id);

        return "redirect:/students";
    }
}
