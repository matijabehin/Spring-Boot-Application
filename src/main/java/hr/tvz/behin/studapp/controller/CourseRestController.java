package hr.tvz.behin.studapp.controller;

import hr.tvz.behin.studapp.dto.CourseDTO;
import hr.tvz.behin.studapp.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseRestController {
    private CourseService service;
    public CourseRestController(CourseService service){ this.service = service; }

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return service.getAllCourses();
    }
    @GetMapping("/{JMBAG}")
    public List<CourseDTO> getCoursesByStudentJmbag(@PathVariable String JMBAG){
        return service.getCoursesByStudentJmbag(JMBAG);
    }
}
