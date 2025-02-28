package ru.hogwarts.demoschool.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;




@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService = new StudentService();

    @PostMapping
    public Student createStudent(@RequestParam String name, @RequestParam int age) {
        return studentService.createStudent(name, age);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents().values().stream().collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestParam String name, @RequestParam int age) {
        return studentService.updateStudent(id, name, age);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/age/{age}")
    public List<Student> filterStudentsByAge(@PathVariable int age) {
        return studentService.getAllStudents().values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
