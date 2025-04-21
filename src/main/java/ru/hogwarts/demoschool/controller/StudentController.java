package ru.hogwarts.demoschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.demoschool.dto.StudentDTO;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.service.FacultyService; 
import ru.hogwarts.demoschool.service.StudentService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

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
        List<Student> students = studentService.getAllStudents();
        return students.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
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

    @GetMapping("/sum")
    public int getSum() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel() // Используем параллельный стрим
                .reduce(0, Integer::sum);
    }
    // Новый эндпоинт для параллельного вывода имен студентов
    @GetMapping("/print-parallel")
    public void printStudentsParallel() {
        List<Student> students = studentService.getAllStudents();

        // Печатаем первые два имени в основном потоке
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        // Печатаем третьего и четвертого студентов в параллельном потоке
        CompletableFuture.runAsync(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        });

        // Печатаем пятого и шестого студентов в еще одном параллельном потоке
        CompletableFuture.runAsync(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        });
    }

    // Новый эндпоинт для синхронизированного вывода имен студентов
    @GetMapping("/print-synchronized")
    public void printStudentsSynchronized() {
        List<Student> students = studentService.getAllStudents();

        // Печатаем первые два имени в основном потоке
        printStudentName(students.get(0).getName());
        printStudentName(students.get(1).getName());

        // Печатаем третьего и четвертого студентов в параллельном потоке
        CompletableFuture.runAsync(() -> {
            printStudentName(students.get(2).getName());
            printStudentName(students.get(3).getName());
        });

        // Печатаем пятого и шестого студентов в еще одном параллельном потоке
        CompletableFuture.runAsync(() -> {
            printStudentName(students.get(4).getName());
            printStudentName(students.get(5).getName());
        });
    }

    // Синхронизированный метод для вывода имени студента
    private synchronized void printStudentName(String name) {
        System.out.println(name);
    }
}