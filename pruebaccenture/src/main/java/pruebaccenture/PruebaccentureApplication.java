package pruebaccenture;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaccentureApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DBUSER", dotenv.get("DBUSER"));
		System.setProperty("DBPASSWORD", dotenv.get("DBPASSWORD"));
		SpringApplication.run(PruebaccentureApplication.class, args);
	}

}
