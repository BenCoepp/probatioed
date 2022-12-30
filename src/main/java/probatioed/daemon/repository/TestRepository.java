package probatioed.daemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import probatioed.daemon.entity.test.Test;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    @Query("select t from Test t where t.depth <= ?1")
    List<Test> findByDepthLessThanEqual(Integer depth);
}
