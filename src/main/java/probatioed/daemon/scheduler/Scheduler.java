package probatioed.daemon.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@Component
public class Scheduler {

    private DoctorRepository doctorRepository;
    private ProjectRepository projectRepository;

    public Scheduler(DoctorRepository doctorRepository, ProjectRepository projectRepository) {
        this.doctorRepository = doctorRepository;
        this.projectRepository = projectRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void executeScheduler() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String[] tasks = {
            "probatioed.daemon.scheduler.tasks.DoctorTask",
            "probatioed.daemon.scheduler.tasks.ProjectTask"
        };
        for (String task : tasks) {
            Class<?> clazz = Class.forName(task);
            Constructor<?> constructor = clazz.getConstructor(DoctorRepository.class, ProjectRepository.class);
            Object instance = constructor.newInstance(doctorRepository, projectRepository);
            Method method;
            try {
                method = instance.getClass().getMethod("execute");
                method.invoke(instance);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
