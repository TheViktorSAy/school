package ru.hogwarts.demoschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.demoschool.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}