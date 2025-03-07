package ru.hogwarts.demoschool.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDfaculty; // Изменено на IDfaculty
    private String namefaculty; // Оставлено без изменений
    private String colorfaculty; // Оставлено без изменений

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students; // Оставлено без изменений

    public Faculty() {
    }

    public Faculty(Long IDfaculty, String namefaculty, String colorfaculty) { // Обновленный конструктор
        this.IDfaculty = IDfaculty;
        this.namefaculty = namefaculty;
        this.colorfaculty = colorfaculty;
    }

    // Геттеры и сеттеры
    public Long getIDfaculty() { // Изменено на getIDfaculty
        return IDfaculty;
    }

    public void setIDfaculty(Long IDfaculty) { // Изменено на setIDfaculty
        this.IDfaculty = IDfaculty;
    }

    public String getNamefaculty() {
        return namefaculty;
    }

    public void setNamefaculty(String namefaculty) {
        this.namefaculty = namefaculty;
    }

    public String getColorfaculty() {
        return colorfaculty;
    }

    public void setColorfaculty(String colorfaculty) {
        this.colorfaculty = colorfaculty;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDfaculty, namefaculty, colorfaculty);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Faculty faculty = (Faculty) obj;
        return Objects.equals(IDfaculty, faculty.IDfaculty) &&
                Objects.equals(namefaculty, faculty.namefaculty) &&
                Objects.equals(colorfaculty, faculty.colorfaculty);
    }
}