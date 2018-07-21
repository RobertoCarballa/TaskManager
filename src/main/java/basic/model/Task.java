package basic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "task")
public class Task implements java.io.Serializable {

	// The ID of the tasks will be auto generated when the tasks are inserted in the
	// database
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// The tasks must have a name, and it's unique
	@Column(nullable = false, unique = true)
	private String name;

	@Column
	private String description;

	@Column
	private LocalDate limitDate;

	@Column
	private Boolean done;

	public Task(String name, String description, LocalDate limitDate, Boolean done) {
		super();
		this.name = name;
		this.description = description;
		this.limitDate = limitDate;
		this.done = done;
	}

	public Task() {

	}

	public Long getId() {
		return id;
	}

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
