package ru.hogwarts.demoschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.demoschool.dto.StudentDTO;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.service.FacultyService; 
import ru.hogwarts.demoschool.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final FacultyService facultyService; // Добавляем FacultyService

    @Autowired
    public StudentController(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService; // Инициализируем FacultyService
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        return studentService.getStudentDTO(id);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/count")
    public long getStudentCount() {
        return studentService.getStudentCount();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/names/start-with-a")
    public List<String> getStudentNamesStartingWithA() {
        return studentService.getAllStudents().stream()
                .map(Student::getName)
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .toList();
    }

    @GetMapping("/longest-faculty-name")
    public String getLongestFacultyName() {
        return facultyService.getAllFaculties().stream()
                .map(Faculty::getNamefaculty)
                .reduce("", (a, b) -> a.length() >= b.length() ? a : b);
    }

    @GetMapping("/sum")
    public int getSum() {
        return (1_000_000 * (1_000_000 + 1)) / 2; // Формула суммы арифметической прогрессии
    }
}