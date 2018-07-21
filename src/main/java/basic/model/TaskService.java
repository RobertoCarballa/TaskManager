package basic.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Long CreateTask(String name, String description, LocalDate limitDate, Boolean done) {

		Task task = new Task(name, description, limitDate, done);

		taskRepository.save(task);

		return task.getId();
	}

	public List<Task> FindTasksInTime(LocalDate limitDate) {

		List<Task> tasks = taskRepository.findActiveTasks(limitDate);

		return tasks;
	}

	// Depending on Boolean value you can find the done or not done tasks
	public List<Task> FindDoneOrNotTasks(Boolean done) {

		List<Task> tasks = taskRepository.findByDone(done);

		return tasks;
	}

	public void DeleteTask(Long id) {

		taskRepository.deleteById(id);
	}

	public void UpdateTask(Long id, String name, String description, LocalDate limitDate, Boolean done) {

		Task taskToUpdate = taskRepository.findOneById(id);

		taskToUpdate.setName(name);
		taskToUpdate.setDescription(description);
		taskToUpdate.setLimitDate(limitDate);
		taskToUpdate.setDone(done);

		taskRepository.save(taskToUpdate);
	}

}
