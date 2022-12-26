package probatioed.daemon.scheduler.tasks;

import probatioed.daemon.entity.App;
import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;

import java.io.IOException;

public class ProjectTask extends SchedulerTask {
    public ProjectTask(DoctorRepository doctorRepository, ProjectRepository projectRepository) {
        super(doctorRepository, projectRepository);
    }

    @Override
    public void execute() throws IOException {
        App app = new App();
        app.init();
        projectRepository.flush();
        projectRepository.saveAll(app.getProjects());
    }
}
