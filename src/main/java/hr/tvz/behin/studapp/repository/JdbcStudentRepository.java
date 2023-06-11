package hr.tvz.behin.studapp.repository;

import hr.tvz.behin.studapp.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcStudentRepository implements StudentRepository {

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    @Autowired
    public JdbcStudentRepository(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;

        this.inserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("students")
                .usingGeneratedKeyColumns("jmbag");
    }

    @Override
    public List<Student> findAll() {
        String query = "SELECT * FROM students;";
        return jdbc.query(query,this::mapRowToStudent);
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String JMBAG) {
        try{
            String query = "SELECT * FROM students WHERE jmbag = ?";
            return Optional.ofNullable(
                    jdbc.queryForObject(query,this::mapRowToStudent,JMBAG)
            );
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> addNewStudent(Student student) {
        String sql = "INSERT INTO students (firstName, lastName, dateOfBirth, jmbag, ects) VALUES (?, ?, ?, ?, ?)";

        try {
            jdbc.update(sql, student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getJmbag(), student.getEcts());
        } catch (DataAccessException e) {
            return Optional.empty();
        }

        return Optional.of(student);
    }

    @Override
    public Optional<Student> updateStudent(String jmbag, Student student) {

        int rowsAffected = jdbc.update("UPDATE students SET firstName = ?, lastName = ?, dateOfBirth = ?, ects = ? WHERE jmbag = ?",
                student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getEcts(), jmbag);

        if(rowsAffected > 0)
            return Optional.of(student);

        return Optional.empty();
    }


    @Override
    public boolean deleteStudent(String JMBAG) {

        jdbc.update("DELETE FROM course_students WHERE student_jmbag = ?", JMBAG);

        int rowsAffected = jdbc.update("DELETE FROM students WHERE jmbag = ?", JMBAG);

        return rowsAffected == 1;
    }

    public Student mapRowToStudent(ResultSet rs, int numRow) throws SQLException {
        return  new Student(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("dateOfBirth").toLocalDate(),
                rs.getString("jmbag"),
                rs.getInt("ects")
        );
    }
}
