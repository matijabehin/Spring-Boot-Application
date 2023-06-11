package hr.tvz.behin.studapp.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentCommand {

    @NotBlank(message = "First name should not be empty")
    private String firstName;

    @NotBlank(message = "Last name should not be empty")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth must be entered")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "JMBAG should not be empty")
    @Pattern(message = "JMBAG must have 10 digits", regexp="[\\d]{10}")
    private String jmbag;

    @NotNull(message = "Number of ECTS points must be entered")
    @PositiveOrZero(message = "Number of ECTS must be entered as a positive integer")
    @Max(message = "Number of ECTS can not be higher than 480", value = 480)
    private Integer ects;
}
