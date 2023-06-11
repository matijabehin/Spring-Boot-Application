package hr.tvz.behin.studapp.repository;

import hr.tvz.behin.studapp.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> findAll();

    Optional<Student> findStudentByJMBAG(String JMBAG);

    Optional<Student> addNewStudent(Student student);

    Optional<Student> updateStudent(String jmbag, Student student);

    boolean deleteStudent(String JMBAG);
}
