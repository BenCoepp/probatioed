package probatioed.daemon.scheduler.tasks;

import probatioed.daemon.entity.App;
import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;
import probatioed.daemon.repository.TestRepository;

import java.io.IOException;

public class ProjectTask extends SchedulerTask {

    public ProjectTask(DoctorRepository doctorRepository, ProjectRepository projectRepository, TestRepository testRepository) {
        super(doctorRepository, projectRepository, testRepository);
    }

    @Override
    public void execute() throws IOException {
        App app = new App();
        app.init();
        projectRepository.deleteAll();
        projectRepository.saveAll(app.getProjects());
    }
}
