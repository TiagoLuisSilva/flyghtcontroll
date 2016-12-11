package br.com.tls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class FlightcontrolApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightcontrolApplication.class, args);
	}
}
