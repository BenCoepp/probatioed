package probatioed.daemon.scheduler.tasks;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import probatioed.daemon.entity.test.Test;
import probatioed.daemon.repository.DoctorRepository;
import probatioed.daemon.repository.ProjectRepository;
import probatioed.daemon.repository.TestRepository;
import probatioed.daemon.utils.DirectoryHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestGathererTask extends SchedulerTask{

    public TestGathererTask(DoctorRepository doctorRepository, ProjectRepository projectRepository, TestRepository testRepository) {
        super(doctorRepository, projectRepository, testRepository);
    }

    @Override
    public void execute() throws IOException {
        String currentDir = System.getProperty("user.dir");
        File optTestDir = new File("./test");
        List<File> files = new ArrayList<>();
        if(optTestDir.isDirectory() && optTestDir.exists()){
            files = DirectoryHelper.getFilesFromDirectory("./test");
        }
        testRepository.deleteAll();
        for (File file : files) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml(new Constructor(Test.class));
            Test test = yaml.load(inputStream);
            test.setRoot(file.getCanonicalPath());
            testRepository.save(test);
        }
    }
}
