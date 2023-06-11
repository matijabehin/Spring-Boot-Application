package hr.tvz.behin.studapp.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private int ects;

    public CourseDTO(Long id, String name, int ects) {
        this.id = id;
        this.name = name;
        this.ects = ects;
    }
}
