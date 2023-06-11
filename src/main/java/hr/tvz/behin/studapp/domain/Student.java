package hr.tvz.behin.studapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    @Id
    private String jmbag;

    private Integer ects;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String jmbag, Integer ects){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.jmbag = jmbag;
        this.ects = ects;
    }
    @ManyToMany(targetEntity = Course.class)
    @JoinTable(
            name = "course_students",
            joinColumns = {@JoinColumn(name = "student_jmbag")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courses;

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", jmbag='" + jmbag + '\'' +
                ", ects=" + ects +
                '}';
    }
}
