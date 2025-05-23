package ru.hogwarts.demoschool.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.demoschool.dto.StudentDTO;
import ru.hogwarts.demoschool.model.Student;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private StudentDTO studentDTO;

    @BeforeEach
    public void setUp() {
        studentDTO = new StudentDTO(null, "Гарри Поттер", 20, 1L);
    }

    @Test
    public void testCreateStudent() {
        ResponseEntity<Student> response = restTemplate.postForEntity("/student", studentDTO, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Гарри Поттер");
    }

    @Test
    public void testGetStudent() {
        // Создайте студента перед тестом, чтобы он существовал в базе данных
        ResponseEntity<Student> createResponse = restTemplate.postForEntity("/student", studentDTO, Student.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<StudentDTO> response = restTemplate.getForEntity("/student/1", StudentDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Гарри Поттер");
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void testUpdateStudent() {
        // Создайте студента перед обновлением
        ResponseEntity<Student> createResponse = restTemplate.postForEntity("/student", studentDTO, Student.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        studentDTO.setId(1L); // Убедитесь, что ID соответствует существующему студенту
        studentDTO.setName("Гермиона");
        restTemplate.put("/student/1", studentDTO);

        ResponseEntity<StudentDTO> response = restTemplate.getForEntity("/student/1", StudentDTO.class);
        assertThat(response.getBody()).isNotNull(); // Проверка на null
        assertThat(response.getBody().getName()).isEqualTo("Гермиона");
    }

    @Test
    public void testDeleteStudent() {
        // Создайте студента перед удалением
        ResponseEntity<Student> createResponse = restTemplate.postForEntity("/student", studentDTO, Student.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        restTemplate.delete("/student/1");
        ResponseEntity<StudentDTO> response = restTemplate.getForEntity("/student/1", StudentDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}