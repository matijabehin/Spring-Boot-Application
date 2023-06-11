package hr.tvz.behin.studapp.service;

import hr.tvz.behin.studapp.command.StudentCommand;
import hr.tvz.behin.studapp.domain.Student;
import hr.tvz.behin.studapp.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDTO> findAll();

    StudentDTO findStudentByJMBAG(String JMBAG);

    Optional<StudentDTO> addNewStudent(StudentCommand command);

    Optional<Student> createStudent(Student student);

    Optional<StudentDTO> updateStudent(String jmbag, Student student);
    boolean deleteStudent(String JMBAG);
}
