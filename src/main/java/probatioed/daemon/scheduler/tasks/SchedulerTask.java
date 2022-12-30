package probatioed.daemon.scheduler.tasks;

import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;
import probatioed.daemon.repository.TestRepository;

import java.io.IOException;

public class SchedulerTask {

    public DoctorRepository doctorRepository;
    public ProjectRepository projectRepository;
    public TestRepository testRepository;
    public SchedulerTask(DoctorRepository doctorRepository, ProjectRepository projectRepository, TestRepository testRepository) {
        this.doctorRepository = doctorRepository;
        this.projectRepository = projectRepository;
        this.testRepository = testRepository;
    }

    public void execute() throws IOException {

    }
}
