package hr.tvz.behin.studapp.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public JobDetail studentPrintJobDetail(){
        return JobBuilder.newJob(StudentPrintJob.class).withIdentity("StudentPrintJob")
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger studentPrintTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10);

        return TriggerBuilder.newTrigger().forJob(studentPrintJobDetail())
                .withIdentity("StudentPrintTrigger").withSchedule(scheduleBuilder).build();
    }
}
