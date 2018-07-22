package basic.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskForm {

	private static final String NOT_BLANK_MESSAGE = "El nombre no puede ser vacio";

	@NotBlank(message = TaskForm.NOT_BLANK_MESSAGE)
	private String name;

	private String description;

	// The format sets how the limitDate will be stored
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate limitDate;

	private Boolean done;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(LocalDate limitDate) {
		this.limitDate = limitDate;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

}
