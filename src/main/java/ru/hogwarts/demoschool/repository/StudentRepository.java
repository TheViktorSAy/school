package ru.hogwarts.demoschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.demoschool.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int min, int max);
}