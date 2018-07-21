package basic.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import basic.model.Task;
import basic.model.TaskNotFoundException;
import basic.model.TaskRepository;

@Controller
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("Tasks")
	public ModelAndView ViewTasks() {
		ModelAndView model = new ModelAndView("viewTasks");
		model.addObject("tasks", taskRepository.findAll());

		return model;
	}

	@GetMapping("Tasks/{limitDate}")
	public List<Task> findByTitle(@PathVariable LocalDate limitDate) {
		return taskRepository.findActiveTasks(limitDate);
	}

	@GetMapping("Tasks/{done}")
	public List<Task> findOne(@PathVariable Boolean done) {
		return taskRepository.findByDone(done);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody Task task) {
		return taskRepository.save(task).getId();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
		taskRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public Long updateBook(@RequestBody Task task, @PathVariable Long id) {
		if (task.getId() != id) {
			// throw new TaskIdMismatchException();
		}
		taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
		return taskRepository.save(task).getId();
	}
}