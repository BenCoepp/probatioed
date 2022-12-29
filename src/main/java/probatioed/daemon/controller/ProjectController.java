package probatioed.daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import probatioed.daemon.entity.App;
import probatioed.daemon.entity.CheckElement;
import probatioed.daemon.entity.Project;
import probatioed.daemon.repository.ProjectRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAll(){
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/test")
    public ResponseEntity<ArrayList<CheckElement>> testProject(@RequestBody Project project) throws IOException {
        ArrayList<CheckElement> elements = new ArrayList<>();
        App app = new App();
        app.init();
        if(app.getProjects().contains(project)){
            //TODO execute test here
        }
        return ResponseEntity.ok(elements);
    }
}
