package ru.hogwarts.demoschool.service;

import ru.hogwarts.demoschool.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long idCounter = 1L;

    public Student createStudent(String name, int age) {
        Student student = new Student(idCounter++, name, age);
        students.put(student.getId(), student);
        return student;
    }

    public Student getStudent(Long id) {
        return students.get(id);
    }

    public Map<Long, Student> getAllStudents() {
        return students;
    }

    public Student updateStudent(Long id, String name, int age) {
        Student student = students.get(id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
        }
        return student;
    }

    public void deleteStudent(Long id) {
        students.remove(id);
    }
}
