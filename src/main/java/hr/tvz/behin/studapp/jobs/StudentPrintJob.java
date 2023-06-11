package hr.tvz.behin.studapp.jobs;

import hr.tvz.behin.studapp.domain.Student;
import hr.tvz.behin.studapp.repository.StudentRepository;
import hr.tvz.behin.studapp.repository.StudentRepositoryImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class StudentPrintJob extends QuartzJobBean {
    private StudentRepositoryImpl repository;

    public StudentPrintJob(StudentRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Student> students = repository.findAll();

        students.forEach(s ->
                System.out.println(s.getJmbag() + " - " + s.getFirstName() + " " + s.getLastName()));
    }
}
