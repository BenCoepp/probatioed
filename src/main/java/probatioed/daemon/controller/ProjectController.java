package probatioed.daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import probatioed.daemon.entity.App;
import probatioed.daemon.entity.CheckElement;
import probatioed.daemon.entity.Project;
import probatioed.daemon.repository.ProjectRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    @GetMapping("/count")
    public ResponseEntity<Integer> count(){
        long count = projectRepository.count();
        return ResponseEntity.ok(Math.toIntExact(count));
    }

    @PostMapping("/{title}/remove")
    public ResponseEntity<Integer> remove(@PathVariable String title) throws IOException {
        App app = new App();
        app.init();
        ArrayList<Project> projects = app.getProjects();
        ListIterator<Project> iter = projects.listIterator();
        while(iter.hasNext()){
            if(iter.next().getTitle().equals(title)){
                iter.remove();
            }
        }
        app.setProjects(projects);
        projectRepository.deleteAll();
        projectRepository.saveAll(projects);
        return ResponseEntity.ok(200);
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
