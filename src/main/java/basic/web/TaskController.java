package basic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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

	@GetMapping("tasks")
	public ModelAndView viewTasks() {
		ModelAndView model = new ModelAndView("viewTasks");
		model.addObject("tasks", taskService.findAll());

		return model;
	}

	@GetMapping("createTask")
	public ModelAndView createTask() {
		ModelAndView model = new ModelAndView("createTask");

		model.addObject("taskForm", new TaskForm());
		return model;
	}

	@PostMapping("createNewTask")
	public String createNewTask(Model model, @Valid @ModelAttribute TaskForm taskForm, Errors errors) {

		if (errors.hasErrors()) {
			return "createTask";
		}
		taskService.createTask(taskForm.getName(), taskForm.getDescription(), taskForm.getLimitDate(),
				taskForm.getDone());

		return "redirect:/tasks";
	}

	@PostMapping("deleteTask")
	public String deleteTask(@RequestParam("r") Long id) {
		taskService.deleteTask(id);

		return "redirect:/tasks";
	}

	@PostMapping("completeTask")
	public String completeTask(@RequestParam("r") Long id) {
		taskService.completeTask(id);

		return "redirect:/tasks";
	}

}