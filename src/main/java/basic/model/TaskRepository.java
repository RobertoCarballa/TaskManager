package basic.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByDone(Boolean done);

	@Query("select a From Task a where a.limitDate> :selectedTime")
	List<Task> findActiveTasks(LocalDate selectedTime);

	Task findOneById(Long id);
}
