package ru.hogwarts.demoschool.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDfaculty;
    private String namefaculty;
    private String colorfaculty;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Указываем, что это "управляющая" ссылка
    private List<Student> students;

    public Faculty() {}

    public Faculty(Long IDfaculty, String namefaculty, String colorfaculty) {
        this.IDfaculty = IDfaculty;
        this.namefaculty = namefaculty;
        this.colorfaculty = colorfaculty;
    }

    public Long getIDfaculty() {
        return IDfaculty;
    }

    public void setIDfaculty(Long IDfaculty) {
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