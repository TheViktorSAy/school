package ru.hogwarts.demoschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.repository.FacultyRepository;
import ru.hogwarts.demoschool.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(String name, int age, Long facultyId) { // Изменено на facultyId
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        Student student = new Student(name, age, faculty);
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) { // Оставлено без изменений
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() { // Оставлено без изменений
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, String name, int age) { // Оставлено без изменений
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Long id) { // Оставлено без изменений
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByAgeRange(int min, int max) { // Оставлено без изменений
        return studentRepository.findByAgeBetween(min, max);
    }
}