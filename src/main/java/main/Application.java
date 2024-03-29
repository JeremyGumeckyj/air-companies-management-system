package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("main.model")
@EnableJpaRepositories("main.repository")
@SpringBootApplication(scanBasePackages = "main")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
