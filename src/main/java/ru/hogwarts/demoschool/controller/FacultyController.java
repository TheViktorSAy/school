package ru.hogwarts.demoschool.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.service.FacultyService;

import java.util.List;
import java.util.stream.Collectors;




@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService = new FacultyService();

    @PostMapping
    public Faculty createFaculty(@RequestParam String name, @RequestParam String color) {
        return facultyService.createFaculty(name, color);
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties().values().stream().collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestParam String name, @RequestParam String color) {
        return facultyService.updateFaculty(id, name, color);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping("/color/{color}")
    public List<Faculty> filterFacultiesByColor(@PathVariable String color) {
        return facultyService.getAllFaculties().values().stream()
                .filter(faculty -> faculty.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
