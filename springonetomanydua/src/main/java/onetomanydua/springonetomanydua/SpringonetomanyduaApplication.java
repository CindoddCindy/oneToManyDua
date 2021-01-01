package onetomanydua.springonetomanydua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringonetomanyduaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringonetomanyduaApplication.class, args);
	}

}
