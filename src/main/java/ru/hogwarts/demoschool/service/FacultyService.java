package ru.hogwarts.demoschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(String namefaculty, String colorfaculty) { // Изменено на namefaculty и colorfaculty
        Faculty faculty = new Faculty(null, namefaculty, colorfaculty);
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long idfaculty) { // Изменено на idfaculty
        return facultyRepository.findById(idfaculty).orElse(null);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty updateFaculty(Long idfaculty, String namefaculty, String colorfaculty) { // Изменено на idfaculty, namefaculty и colorfaculty
        Faculty faculty = facultyRepository.findById(idfaculty).orElse(null);
        if (faculty != null) {
            faculty.setNamefaculty(namefaculty); // Изменено на setNamefaculty
            faculty.setColorfaculty(colorfaculty); // Изменено на setColorfaculty
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void deleteFaculty(Long idfaculty) { // Изменено на idfaculty
        facultyRepository.deleteById(idfaculty);
    }

    public List<Faculty> searchFaculties(String query) {
        return facultyRepository.findByNamefacultyIgnoreCaseContainingOrColorfacultyIgnoreCaseContaining(query, query); // Изменено на namefaculty и colorfaculty
    }

    public List<Student> getStudentsByFaculty(Long idfaculty) { // Изменено на idfaculty
        Faculty faculty = facultyRepository.findById(idfaculty).orElse(null);
        return faculty != null ? faculty.getStudents() : null;
    }
}