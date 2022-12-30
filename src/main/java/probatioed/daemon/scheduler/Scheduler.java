package probatioed.daemon.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;
import probatioed.daemon.repository.TestRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@Component
public class Scheduler {

    private DoctorRepository doctorRepository;
    private ProjectRepository projectRepository;
    private TestRepository testRepository;

    public Scheduler(DoctorRepository doctorRepository, ProjectRepository projectRepository, TestRepository testRepository) {
        this.doctorRepository = doctorRepository;
        this.projectRepository = projectRepository;
        this.testRepository = testRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void executeScheduler() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
        String[] tasks = {
            "probatioed.daemon.scheduler.tasks.DoctorTask",
            "probatioed.daemon.scheduler.tasks.ProjectTask",
            "probatioed.daemon.scheduler.tasks.TestGathererTask"
        };
        for (String task : tasks) {
            new Thread(() ->{
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(task);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Constructor<?> constructor = null;
                try {
                    constructor = clazz.getConstructor(DoctorRepository.class, ProjectRepository.class, TestRepository.class);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                Object instance = null;
                try {
                    instance = constructor.newInstance(doctorRepository, projectRepository, testRepository);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                Method method;
                try {
                    method = instance.getClass().getMethod("execute");
                    method.invoke(instance);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }){{start();}}.join();
        }
    }
}
