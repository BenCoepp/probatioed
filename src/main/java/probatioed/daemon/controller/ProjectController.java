package probatioed.daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import probatioed.daemon.entity.Project;
import probatioed.daemon.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

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
}
