package pruebaccenture;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class PruebaccentureApplication {
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_CONNECTION_URL", dotenv.get("DB_CONNECTION_URL"));
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		SpringApplication.run(PruebaccentureApplication.class, args);
	}

}
