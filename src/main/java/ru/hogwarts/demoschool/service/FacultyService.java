package ru.hogwarts.demoschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(String namefaculty, String colorfaculty) {
        logger.info("Was invoked method for createFaculty with name: {} and color: {}", namefaculty, colorfaculty);
        Faculty faculty = new Faculty(null, namefaculty, colorfaculty);
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long idfaculty) {
        logger.info("Was invoked method for getFaculty with id: {}", idfaculty);
        return facultyRepository.findById(idfaculty).orElse(null);
    }

    public List<Faculty> getAllFaculties() {
        logger.info("Was invoked method for getAllFaculties");
        return facultyRepository.findAll();
    }

    public Faculty updateFaculty(Long idfaculty, String namefaculty, String colorfaculty) {
        logger.info("Was invoked method for updateFaculty with id: {}", idfaculty);
        Faculty faculty = facultyRepository.findById(idfaculty).orElse(null);
        if (faculty != null) {
            faculty.setNamefaculty(namefaculty);
            faculty.setColorfaculty(colorfaculty);
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void deleteFaculty(Long idfaculty) {
        logger.info("Was invoked method for deleteFaculty with id: {}", idfaculty);
        facultyRepository.deleteById(idfaculty);
    }

    public List<Faculty> searchFaculties(String query) {
        logger.info("Was invoked method for searchFaculties with query: {}", query);
        return facultyRepository.findByNamefacultyIgnoreCaseContainingOrColorfacultyIgnoreCaseContaining(query, query);
    }

    public List<Student> getStudentsByFaculty(Long idfaculty) {
        logger.info("Was invoked method for getStudentsByFaculty with id: {}", idfaculty);
        Faculty faculty = facultyRepository.findById(idfaculty).orElse(null);
        return faculty != null ? faculty.getStudents() : new ArrayList<>();
    }
}