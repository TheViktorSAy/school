package ru.hogwarts.demoschool.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.demoschool.dto.StudentDTO;
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.service.StudentService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService; // Используйте @MockBean вместо @Mock

    private StudentDTO studentDTO;

    @BeforeEach
    public void setUp() {
        studentDTO = new StudentDTO(1L, "Гарри Поттер", 20, 1L);
    }

    @Test
    public void testCreateStudent() throws Exception {
        when(studentService.createStudent(any())).thenReturn(new Student("Гарри Поттер", 20, new Faculty(1L, "Грифендор", "Красный")));

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Гарри Поттер"));
    }

    @Test
    public void testGetStudent() throws Exception {
        when(studentService.getStudentDTO(1L)).thenReturn(studentDTO);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Гарри Поттер"));
    }

    @Test
    public void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Collections.singletonList(new Student("Гарри Поттер", 20, new Faculty(1L, "Грифендор", "Красный"))));

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Гарри Поттер"));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        studentDTO.setName("Гермиона");
        when(studentService.updateStudent(any(), any())).thenReturn(new Student("Гермиона", 20, new Faculty(1L, "Грифендор", "Красный")));

        mockMvc.perform(put("/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Гермиона"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isNoContent());
    }
}