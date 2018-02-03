package cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.framework.configuration",
        "cabanas.garcia.ismael.dddinpractice.atm.infrastructure.framework.configuration",
        "cabanas.garcia.ismael.dddinpractice.management.infrastructure.framework.configuration"
    })
@SuppressWarnings("PMD.UseUtilityClass")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
