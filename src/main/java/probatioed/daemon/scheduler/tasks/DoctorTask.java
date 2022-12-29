package probatioed.daemon.scheduler.tasks;

import probatioed.daemon.entity.CheckElement;
import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;

import java.io.IOException;
import java.util.ArrayList;

import static probatioed.daemon.utils.DoctorHelper.*;

public class DoctorTask extends SchedulerTask{
    public DoctorTask(DoctorRepository doctorRepository, ProjectRepository projectRepository) {
        super(doctorRepository, projectRepository);
    }

    @Override
    public void execute() throws IOException {
        ArrayList<CheckElement> elements = new ArrayList<>();
        elements.add(checkDocker());
        elements.add(checkDockerCompose());
        elements.add(checkKubectl());
        elements.add(checkPodman());
        elements.add(checkInternetConnected());
        doctorRepository.deleteAll();
        doctorRepository.saveAll(elements);
    }
}
