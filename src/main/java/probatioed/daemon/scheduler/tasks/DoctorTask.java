package probatioed.daemon.scheduler.tasks;

import probatioed.daemon.entity.CheckElement;
import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;
import probatioed.daemon.repository.TestRepository;

import java.io.IOException;
import java.util.ArrayList;

import static probatioed.daemon.utils.DoctorHelper.*;

public class DoctorTask extends SchedulerTask{

    public DoctorTask(DoctorRepository doctorRepository, ProjectRepository projectRepository, TestRepository testRepository) {
        super(doctorRepository, projectRepository, testRepository);
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
