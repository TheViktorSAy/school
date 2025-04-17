package ru.hogwarts.demoschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.demoschool.dto.StudentDTO;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.repository.FacultyRepository;
import ru.hogwarts.demoschool.repository.StudentRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(StudentDTO studentDTO) {
        logger.info("Was invoked method for createStudent with name: {} and age: {}", studentDTO.getName(), studentDTO.getAge());
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        Student student = new Student(studentDTO.getName(), studentDTO.getAge(), faculty);
        return studentRepository.save(student);
    }

    public StudentDTO getStudentDTO(Long id) {
        logger.info("Was invoked method for getStudentDTO with id: {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            return new StudentDTO(student.getID(), student.getName(), student.getAge(), student.getFaculty().getIDfaculty());
        }
        return null; // Или выбросьте исключение, если студент не найден
    }

    public List<Student> getAllStudents() {
        logger.info("Was invoked method for getAllStudents");
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, StudentDTO studentDTO) {
        logger.info("Was invoked method for updateStudent with id: {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(studentDTO.getName());
            student.setAge(studentDTO.getAge());
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method for deleteStudent with id: {}", id);
        studentRepository.deleteById(id);
    }

    public long getStudentCount() {
        logger.info("Was invoked method for getStudentCount");
        return studentRepository.countStudents();
    }

    public double getAverageAge() {
        logger.info("Was invoked method for getAverageAge");
        return studentRepository.averageAge();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method for getLastFiveStudents");
        return studentRepository.findTop5ByOrderByIdDesc();
    }
}