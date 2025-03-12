package sr.scom.taskman.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sr.scom.taskman.taskmanagement.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
