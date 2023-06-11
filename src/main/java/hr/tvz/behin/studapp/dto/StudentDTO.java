package hr.tvz.behin.studapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String firstName;

    private String lastName;

    private String jmbag;

    private Integer ects;

    private boolean payingStudying;

}
