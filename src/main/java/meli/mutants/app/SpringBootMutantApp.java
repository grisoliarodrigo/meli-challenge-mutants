package meli.mutants.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
 
@SpringBootApplication(scanBasePackages={"meli.mutants"})
public class SpringBootMutantApp {
 
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMutantApp.class, args);
    }
}

