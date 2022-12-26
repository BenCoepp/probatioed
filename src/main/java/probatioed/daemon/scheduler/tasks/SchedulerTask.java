package probatioed.daemon.scheduler.tasks;

import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;

import java.io.IOException;

public class SchedulerTask {

    public DoctorRepository doctorRepository;
    public ProjectRepository projectRepository;
    public SchedulerTask(DoctorRepository doctorRepository, ProjectRepository projectRepository) {
        this.doctorRepository = doctorRepository;
        this.projectRepository = projectRepository;
    }

    public void execute() throws IOException {

    }
}
