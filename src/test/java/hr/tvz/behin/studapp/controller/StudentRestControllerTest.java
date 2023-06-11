package hr.tvz.behin.studapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hr.tvz.behin.studapp.command.StudentCommand;
import hr.tvz.behin.studapp.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final StudentCommand STUDENT = new StudentCommand("Test", "Test", LocalDate.now().minusYears(25),
            "1111111111",180);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllStudents() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/students")
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
    void getStudentByJMBAG() throws Exception {
        String JMBAG_TEST = "3456789012";

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/students/{JMBAG}", JMBAG_TEST)
                                .with(user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").exists())
                .andExpect(jsonPath("$.lastName").exists())
                .andExpect(jsonPath("$.jmbag").exists())
                .andExpect(jsonPath("$.ects").exists())
                .andExpect(jsonPath("$.payingStudying").exists());
    }

    @Test
    @DirtiesContext
    void addNewStudent() throws Exception {

        objectMapper.registerModule(new JavaTimeModule());

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/students")
                                .with(user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(STUDENT))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(STUDENT.getJmbag()))
                .andExpect(jsonPath("$.firstName").value(STUDENT.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(STUDENT.getLastName()));
    }

    @Test
    void updateStudent() throws Exception {

        objectMapper.registerModule(new JavaTimeModule());

        String JMBAG = "1234567890";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/students/{JMBAG}", JMBAG)
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(STUDENT))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(STUDENT.getJmbag()))
                .andExpect(jsonPath("$.firstName").value(STUDENT.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(STUDENT.getLastName()));
    }

    @Test
    @DirtiesContext
    void deleteStudent() throws Exception {
        String JMBAG = "1234567890";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/students/{JMBAG}",JMBAG)
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
        )
        .andExpect(status().isOk());

    }
}