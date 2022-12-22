package probatioed.daemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import probatioed.daemon.entity.CheckElement;

public interface DoctorRepository extends JpaRepository<CheckElement, Integer> {

}
