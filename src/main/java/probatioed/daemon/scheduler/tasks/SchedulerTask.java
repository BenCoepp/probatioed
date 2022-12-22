package probatioed.daemon.scheduler.tasks;

import probatioed.daemon.repository.DoctorRepository;

import java.io.IOException;

public class SchedulerTask {

    public DoctorRepository doctorRepository;
    public SchedulerTask(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void execute() throws IOException {

    }
}
