package basic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import basic.model.TaskForm;
import basic.model.TaskService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("Tasks")
	public ModelAndView viewTasks() {
		ModelAndView model = new ModelAndView("viewTasks");
		model.addObject("tasks", taskService.findAll());

		return model;
	}

	@GetMapping("CreateTask")
	public ModelAndView createTask() {
		ModelAndView model = new ModelAndView("createTask");

		model.addObject("taskForm", new TaskForm());
		return model;
	}

	@PostMapping("createNewTask")
	public String createNewTask(Model model, @ModelAttribute TaskForm taskForm) {

		taskService.createTask(taskForm.getName(), taskForm.getDescription(), taskForm.getLimitDate(),
				taskForm.getDone());

		return "redirect:/Tasks";
	}

	@PostMapping("deleteTask")
	public String deleteTask(@RequestParam("r") Long id) {
		taskService.deleteTask(id);

		return "redirect:/Tasks";
	}

	@PostMapping("completeTask")
	public String completeTask(@RequestParam("r") Long id) {
		taskService.completeTask(id);

		return "redirect:/Tasks";
	}

}