package hr.tvz.behin.studapp.repository;

import hr.tvz.behin.studapp.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
    @Query("SELECT c FROM Course c INNER JOIN CourseStudent cs ON c.courseID = cs.course_id WHERE cs.student_jmbag = :jmbag")
    List<Course> findCoursesByStudentJmbag(@Param("jmbag") String jmbag);
}

