package hr.tvz.behin.studapp.repository;

import hr.tvz.behin.studapp.domain.Student;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    private List<Student> studentList;


    public StudentRepositoryImpl(){
        List<Student> students = new ArrayList<>();

        students.add(new Student("Ivan", "Ivic", LocalDate.now().minusYears(29).minusDays(66),
                "0243235445", 120));

        students.add(new Student("Marko","Markic",LocalDate.now().minusYears(21).minusDays(13),
                "0243235337",180));

        students.add(new Student("Ivo","Ivic",LocalDate.now().minusYears(23).minusDays(123),
                "0243234432",300));

        this.studentList = students;
    }
    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String JMBAG) {
        Optional<Student> student = studentList.stream().filter(s -> s.getJmbag().equals(JMBAG)).findAny();

        return student;
    }

    @Override
    public Optional<Student> addNewStudent(Student student) {
        Optional<Student> alreadyExistingStudent = findStudentByJMBAG(student.getJmbag());
        if(alreadyExistingStudent.isEmpty()){
            studentList.add(student);
            return Optional.of(student);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Student> updateStudent(String jmbag, Student student) {
        return Optional.empty();
    }

    @Override
    public boolean deleteStudent(String JMBAG) {
        for(int i=0;i<studentList.size();i++){
            if(studentList.get(i).getJmbag().equals(JMBAG)){
                studentList.remove(i);
                return true;
            }
        }
        return false;
    }
}
