/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.Aptech_eProject.comtroller;

import com.aptech.Aptech_eProject.model.Student;
import com.aptech.Aptech_eProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String listStudent(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("student", students);
        return "student/list";
    }

    // Hiển thị form thêm sinh viên mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/create";
    }

    // Xử lý thêm sinh viên mới
    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/student";
    }

    // Hiển thị form cập nhật sinh viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "student/edit";
    }

    // Xử lý cập nhật sinh viên
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        student.setId(id);
        studentRepository.save(student);
        return "redirect:/student";
    }

    // Xóa sinh viên
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/student";
    }
}