package ru.hogwarts.demoschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.demoschool.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNamefacultyIgnoreCaseContainingOrColorfacultyIgnoreCaseContaining(String namefaculty, String colorfaculty); // Изменено на namefaculty и colorfaculty
}