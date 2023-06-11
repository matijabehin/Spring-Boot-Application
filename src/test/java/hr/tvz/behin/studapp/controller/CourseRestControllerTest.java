package hr.tvz.behin.studapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class CourseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCourses() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/courses")
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getCoursesByStudentJmbag() throws Exception {
        String JMBAG_TEST = "1234567890";

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/courses/{JMBAG}", JMBAG_TEST)
                                .with(user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getCoursesBySecondStudentJmbag() throws Exception {
        String JMBAG_TEST = "2345678901";

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/courses/{JMBAG}", JMBAG_TEST)
                                .with(user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}