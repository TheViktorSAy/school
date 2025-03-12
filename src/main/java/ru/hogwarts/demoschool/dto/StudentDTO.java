package ru.hogwarts.demoschool.dto;

public class StudentDTO {
    private Long ID; // Изменено на ID
    private String name;
    private int age;
    private Long facultyId; // Изменено на facultyId
    private String facultyColor; // Изменено на facultyColor

    public StudentDTO(Long ID, String name, int age, Long facultyId, String facultyColor) { // Изменено на ID
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.facultyId = facultyId;
        this.facultyColor = facultyColor;
    }

    // Getters and Setters
    public Long getID() { // Изменено на getID
        return ID;
    }

    public void setID(Long ID) { // Изменено на setID
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getFacultyId() { // Изменено на getFacultyId
        return facultyId;
    }

    public void setFacultyId(Long facultyId) { // Изменено на setFacultyId
        this.facultyId = facultyId;
    }

    public String getFacultyColor() { // Изменено на getFacultyColor
        return facultyColor;
    }

    public void setFacultyColor(String facultyColor) { // Изменено на setFacultyColor
        this.facultyColor = facultyColor;
    }
}