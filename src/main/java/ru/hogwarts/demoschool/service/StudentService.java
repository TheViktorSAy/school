package ru.hogwarts.demoschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.demoschool.dto.StudentDTO;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.repository.FacultyRepository;
import ru.hogwarts.demoschool.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(StudentDTO studentDTO) {
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        Student student = new Student(studentDTO.getName(), studentDTO.getAge(), faculty);
        return studentRepository.save(student);
    }

    public StudentDTO getStudentDTO(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            return new StudentDTO(student.getID(), student.getName(), student.getAge(), student.getFaculty().getIDfaculty());
        }
        return null; // Или выбросьте исключение, если студент не найден
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(studentDTO.getName());
            student.setAge(studentDTO.getAge());
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public long getStudentCount() {
        return studentRepository.countStudents();
    }

    public double getAverageAge() {
        return studentRepository.averageAge();
    }

    public List<Student> getLastFiveStudents() {
        return studentRepository.findTop5ByOrderByIdDesc();
    }
}