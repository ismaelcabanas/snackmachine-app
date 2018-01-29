package cabanas.garcia.ismael.snackmachine.infrastructure.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "cabanas.garcia.ismael.snackmachine.infrastructure.framework.configuration",
        "cabanas.garcia.ismael.atm.infrastructure.framework.configuration"
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
