package ru.hogwarts.demoschool.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.demoschool.model.Faculty;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private Faculty faculty;

    @BeforeEach
    public void setUp() {
        faculty = new Faculty(null, "Грифендор", "Красный");
    }

    @Test
    public void testCreateFaculty() {
        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNamefaculty()).isEqualTo("Грифендор");
    }

    @Test
    public void testGetFaculty() {
        // Создайте факультет перед тестом, чтобы он существовал в базе данных
        ResponseEntity<Faculty> createResponse = restTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNamefaculty()).isEqualTo("Грифендор");
    }

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("/faculty", Faculty[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void testUpdateFaculty() {
        // Создайте факультет перед обновлением
        ResponseEntity<Faculty> createResponse = restTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        faculty.setIDfaculty(1L); // Убедитесь, что ID соответствует существующему факультету
        faculty.setNamefaculty("Слизерин");
        restTemplate.put("/faculty/1", faculty);

        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertThat(response.getBody()).isNotNull(); // Проверка на null
        assertThat(response.getBody().getNamefaculty()).isEqualTo("Слизерин");
    }

    @Test
    public void testDeleteFaculty() {
        // Создайте факультет перед удалением
        ResponseEntity<Faculty> createResponse = restTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        restTemplate.delete("/faculty/1");
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}