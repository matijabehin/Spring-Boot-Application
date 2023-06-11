package hr.tvz.behin.studapp.service;

import hr.tvz.behin.studapp.domain.Course;
import hr.tvz.behin.studapp.dto.CourseDTO;
import hr.tvz.behin.studapp.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository repository;
    public List<CourseDTO> getAllCourses(){
        List<Course> courses = repository.findAll();
        return courses.stream()
                .map(this::mapCourseToDTO)
                .collect(Collectors.toList());
    }
    public List<CourseDTO> getCoursesByStudentJmbag(String jmbag){
        List<Course> courses = repository.findCoursesByStudentJmbag(jmbag);
        return courses.stream()
                .map(this::mapCourseToDTO)
                .collect(Collectors.toList());
    }
    public CourseDTO mapCourseToDTO(Course course){
        return new CourseDTO(course.getCourseID(), course.getName(), course.getEcts());
    }
}
