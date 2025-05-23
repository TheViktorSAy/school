package ru.hogwarts.demoschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestParam String namefaculty, @RequestParam String colorfaculty) {
        return facultyService.createFaculty(namefaculty, colorfaculty);
    }

    @GetMapping("/{idfaculty}")
    public Faculty getFaculty(@PathVariable Long idfaculty) {
        return facultyService.getFaculty(idfaculty);
    }

    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @PutMapping("/{idfaculty}")
    public Faculty updateFaculty(@PathVariable Long idfaculty, @RequestParam String namefaculty, @RequestParam String colorfaculty) {
        return facultyService.updateFaculty(idfaculty, namefaculty, colorfaculty);
    }


    @DeleteMapping("/{idfaculty}")
    public void deleteFaculty(@PathVariable Long idfaculty) {
        facultyService.deleteFaculty(idfaculty);
    }

    @GetMapping("/search")
    public List<Faculty> searchFaculties(@RequestParam String query) {
        return facultyService.searchFaculties(query);
    }

    @GetMapping("/{idfaculty}/students")
    public List<Student> getStudentsByFaculty(@PathVariable Long idfaculty) {
        return facultyService.getStudentsByFaculty(idfaculty);
    }
    @GetMapping("/longest-faculty-name")
    public String getLongestFacultyName() {
        return facultyService.getAllFaculties().stream()
                .map(Faculty::getNamefaculty)
                .reduce("", (a, b) -> a.length() >= b.length() ? a : b);
    }

}