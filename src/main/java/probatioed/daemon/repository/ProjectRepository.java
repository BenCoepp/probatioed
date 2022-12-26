package probatioed.daemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import probatioed.daemon.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
