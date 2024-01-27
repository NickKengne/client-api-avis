package codex.avisclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class AvisClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvisClientApplication.class, args);
	}

}
