package ru.hogwarts.demoschool.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID; // Поле ID

    private String name; // Поле name
    private int age; // Поле age

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty; // Связь с Faculty

    // Конструкторы
    public Student() {}

    public Student(String name, int age, Faculty faculty) {
        this.name = name;
        this.age = age;
        this.faculty = faculty;
    }

    // Геттеры и сеттеры
    public Long getID() { // Метод getID
        return ID;
    }

    public void setID(Long ID) { // Метод setID
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return age == student.age &&
                Objects.equals(ID, student.ID) &&
                Objects.equals(name, student.name);
    }
}