package basic.model;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Long createTask(String name, String description, LocalDate limitDate, Boolean done) {

		Task task = new Task(name, description, limitDate, done);

		taskRepository.save(task);

		return task.getId();
	}

	@PostConstruct
	protected void initialize() {
		Task task1 = new Task("tarea 1", "tarea de prueba", LocalDate.now(), false);
		Task task2 = new Task("tarea 2", "tarea de prueba", LocalDate.now().minusDays(2), true);
		Task task3 = new Task("tarea 3", "tarea de prueba", LocalDate.now().plusDays(2), false);

		taskRepository.save(task1);
		taskRepository.save(task2);
		taskRepository.save(task3);
	}

	public List<Task> findTasksInTime(LocalDate limitDate) {

		List<Task> tasks = taskRepository.findActiveTasks(limitDate);

		return tasks;
	}

	// Depending on Boolean value you can find the done or not done tasks
	public List<Task> findDoneOrNotTasks(Boolean done) {

		List<Task> tasks = taskRepository.findByDone(done);

		return tasks;
	}

	public void feleteTask(Long id) {

		taskRepository.deleteById(id);
	}

	public void updateTask(Long id, String name, String description, LocalDate limitDate, Boolean done) {

		Task taskToUpdate = taskRepository.findOneById(id);

		taskToUpdate.setName(name);
		taskToUpdate.setDescription(description);
		taskToUpdate.setLimitDate(limitDate);
		taskToUpdate.setDone(done);

		taskRepository.save(taskToUpdate);
	}

}
