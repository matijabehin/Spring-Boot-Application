package hr.tvz.behin.studapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseID;
    private String name;
    @Column(name = "ects_points")
    private int ects;

    public Course(Long id, String name, int ects){
        this.courseID = id;
        this.name = name;
        this.ects = ects;
    }
}
