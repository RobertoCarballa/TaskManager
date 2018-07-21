package basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//Search for repositories in that package
@EnableJpaRepositories("basic.model")
//Search for Entities in that package
@EntityScan("basic.model")
//Search for the properties file
@PropertySource("persistence.properties")
//Main class needed to run Spring-boot on the App
@SpringBootApplication
public class TaskManager {
	public static void main(String[] args) {
		SpringApplication.run(TaskManager.class, args);
	}
}