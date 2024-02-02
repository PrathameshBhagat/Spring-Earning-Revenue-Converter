package com.prathamesh.revenuecalculator;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class RevenuecalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevenuecalculatorApplication.class, args);
	}

	@GetMapping("/")
	public String getMethodName() {
		return "Home.html";
	}

}
