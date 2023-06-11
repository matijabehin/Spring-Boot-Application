package hr.tvz.behin.studapp.service;

import hr.tvz.behin.studapp.command.StudentCommand;
import hr.tvz.behin.studapp.domain.Student;
import hr.tvz.behin.studapp.dto.StudentDTO;
import hr.tvz.behin.studapp.repository.JdbcStudentRepository;
import hr.tvz.behin.studapp.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    private static final int YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED = 26;

    //private StudentRepository studentRepository;

    private JdbcStudentRepository studentRepository;

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(this::mapStudentsToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO findStudentByJMBAG(String JMBAG) {
        return studentRepository.findStudentByJMBAG(JMBAG).map(this::mapStudentsToDTO).orElse(null);
    }

    @Override
    public Optional<StudentDTO> addNewStudent(StudentCommand command) {
        return studentRepository.addNewStudent(mapCommandToStudent(command)).map(this::mapStudentsToDTO);
    }

    @Override
    public Optional<Student> createStudent(Student student) {
        return studentRepository.addNewStudent(student);
    }

    @Override
    public Optional<StudentDTO> updateStudent(String jmbag, Student student) {
        return Optional.of(mapStudentsToDTO(studentRepository.updateStudent(jmbag, student).get()));
    }

    @Override
    public boolean deleteStudent(String JMBAG) {
        return studentRepository.deleteStudent(JMBAG);
    }

    public StudentDTO mapStudentsToDTO(Student student){
        boolean payingTutor = ChronoUnit.YEARS.between(student.getDateOfBirth(), LocalDate.now()) > YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED;
        return new StudentDTO(student.getFirstName(), student.getLastName(), student.getJmbag(),student.getEcts(), payingTutor);
    }

    public Student mapCommandToStudent(StudentCommand command){
        return new Student(command.getFirstName(), command.getLastName(), command.getDateOfBirth(),
                command.getJmbag(), command.getEcts());
    }
}
