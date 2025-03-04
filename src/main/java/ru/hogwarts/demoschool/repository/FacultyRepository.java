package ru.hogwarts.demoschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.demoschool.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}