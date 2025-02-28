package ru.hogwarts.demoschool.service;

import ru.hogwarts.demoschool.model.Faculty;

import java.util.HashMap;
import java.util.Map;

public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long idCounter = 1L;

    public Faculty createFaculty(String name, String color) {
        Faculty faculty = new Faculty(idCounter++, name, color);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        return faculties.get(id);
    }

    public Map<Long, Faculty> getAllFaculties() {
        return faculties;
    }

    public Faculty updateFaculty(Long id, String name, String color) {
        Faculty faculty = faculties.get(id);
        if (faculty != null) {
            faculty.setName(name);
            faculty.setColor (color);
        }
        return faculty;
    }

    public void deleteFaculty(Long id) {
        faculties.remove(id);
    }
}
