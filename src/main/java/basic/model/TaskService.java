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

	// Some Test Tasks will be inserted in the Database to interact with the app
	@PostConstruct
	protected void initialize() {
		Task task1 = new Task("tarea 1", "tarea de prueba", LocalDate.now(), false);
		Task task2 = new Task("tarea 2", "tarea de prueba", LocalDate.now().minusDays(2), true);
		Task task3 = new Task("tarea 3", "tarea de prueba", LocalDate.now().plusDays(2), false);

		taskRepository.save(task1);
		taskRepository.save(task2);
		taskRepository.save(task3);
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public Long createTask(String name, String description, LocalDate limitDate, Boolean done) {

		Task task = new Task(name, description, limitDate, done);

		taskRepository.save(task);

		return task.getId();
	}

	public void deleteTask(Long id) {

		taskRepository.deleteById(id);
	}

	public void completeTask(Long id) {

		Task taskToUpdate = taskRepository.findOneById(id);

		taskToUpdate.setDone(true);

		taskRepository.save(taskToUpdate);
	}

}
