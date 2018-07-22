package basic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import basic.model.Task;
import basic.model.TaskForm;
import basic.model.TaskRepository;

@Controller
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("Tasks")
	public ModelAndView viewTasks() {
		ModelAndView model = new ModelAndView("viewTasks");
		model.addObject("tasks", taskRepository.findAll());

		return model;
	}

	@GetMapping("CreateTask")
	public ModelAndView createTask() {
		ModelAndView model = new ModelAndView("createTask");

		model.addObject("taskForm", new TaskForm());
		return model;
	}

	@PostMapping("createNewTask")
	public ModelAndView createNewTask(Model model, @ModelAttribute TaskForm taskForm) {

		Task task = new Task();
		task.setName(taskForm.getName());
		task.setDescription(taskForm.getDescription());
		task.setLimitDate(taskForm.getLimitDate());
		task.setDone(taskForm.getDone());
		taskRepository.save(task);

		ModelAndView modelAndView = new ModelAndView("viewTasks");
		return modelAndView;
	}

	@PostMapping("deleteTask")
	public String deleteTask(@RequestParam("r") Long id) {
		taskRepository.deleteById(id);

		return "redirect:/Tasks";
	}

}