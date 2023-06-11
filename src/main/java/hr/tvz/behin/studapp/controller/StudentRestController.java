package hr.tvz.behin.studapp.controller;

import hr.tvz.behin.studapp.command.StudentCommand;
import hr.tvz.behin.studapp.domain.Student;
import hr.tvz.behin.studapp.dto.StudentDTO;
import hr.tvz.behin.studapp.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StudentRestController {

    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{JMBAG}")
    public ResponseEntity<StudentDTO> getStudentByJMBAG(@PathVariable String JMBAG){
        StudentDTO student = studentService.findStudentByJMBAG(JMBAG);
        if(student != null){
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addNewStudent(@Valid @RequestBody StudentCommand command){
        return studentService.addNewStudent(command)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @PutMapping("/{JMBAG}")
    public Optional<StudentDTO> updateStudent(@PathVariable String JMBAG, @RequestBody Student student){
        return studentService.updateStudent(JMBAG, student);
    }

    @PostMapping("/add")
    public Optional<Student> createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @DeleteMapping("/{JMBAG}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable String JMBAG){
        boolean isStudentDeleted = studentService.deleteStudent(JMBAG);

        if(isStudentDeleted){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
