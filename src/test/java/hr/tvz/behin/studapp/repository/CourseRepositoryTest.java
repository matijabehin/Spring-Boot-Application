package hr.tvz.behin.studapp.repository;

import hr.tvz.behin.studapp.domain.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findAll() {
        List<Course> courses = courseRepository.findAll();
        assertNotNull(courses);
    }

    @Test
    void findCoursesByStudentJmbag() {
        String JMBAG = "1234567890";

        List<Course> courses = courseRepository.findCoursesByStudentJmbag(JMBAG);
        assertNotNull(courses);
    }
}
