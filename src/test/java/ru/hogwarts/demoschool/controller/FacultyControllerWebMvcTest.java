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
import ru.hogwarts.demoschool.model.Faculty;
import ru.hogwarts.demoschool.service.FacultyService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    private Faculty faculty;

    @BeforeEach
    public void setUp() {
        faculty = new Faculty(1L, "Гифендор", "Красный");
    }

    @Test
    public void testCreateFaculty() throws Exception {
        when(facultyService.createFaculty(any(), any())).thenReturn(faculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(faculty)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.namefaculty").value("Грифендор"));
    }

    @Test
    public void testGetFaculty() throws Exception {
        when(facultyService.getFaculty(1L)).thenReturn(faculty);

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.namefaculty").value("Грифендор"));
    }

    @Test
    public void testGetAllFaculties() throws Exception {
        when(facultyService.getAllFaculties()).thenReturn(Collections.singletonList(faculty));

        mockMvc.perform(get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].namefaculty").value("Грифендор"));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        faculty.setNamefaculty("Слизерин");
        when(facultyService.updateFaculty(any(), any(), any())).thenReturn(faculty);

        mockMvc.perform(put("/faculty/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(faculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.namefaculty").value("Слизерин"));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isNoContent());
    }
}
