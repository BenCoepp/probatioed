package probatioed.daemon.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import probatioed.daemon.repository.DoctorRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@Component
public class Scheduler {

    private DoctorRepository doctorRepository;

    public Scheduler(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void executeScheduler() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String[] tasks = {
          "probatioed.daemon.scheduler.tasks.DoctorTask"
        };
        for (String task : tasks) {
            Class<?> clazz = Class.forName(task);
            Constructor<?> constructor = clazz.getConstructor(DoctorRepository.class);
            Object instance = constructor.newInstance(doctorRepository);
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
