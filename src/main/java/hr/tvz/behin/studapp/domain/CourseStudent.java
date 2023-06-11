package hr.tvz.behin.studapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_students")
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudent {
    @Id
    private String student_jmbag;
    @Id
    private Long course_id;
}
