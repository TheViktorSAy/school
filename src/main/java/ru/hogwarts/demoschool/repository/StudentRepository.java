package ru.hogwarts.demoschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.demoschool.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int min, int max);

    @Query("SELECT COUNT(s) FROM Student s")
    long countStudents();

    @Query("SELECT AVG(s.age) FROM Student s")
    double averageAge();

    @Query("SELECT s FROM Student s ORDER BY s.ID DESC")
    List<Student> findTop5ByOrderByIdDesc();
}