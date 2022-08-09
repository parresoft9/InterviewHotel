package dev.parresoft9.hotelbeds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.hotelbeds.supplierintegrations.hackertest.detector")
public class HotelbedsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelbedsApplication.class, args);
	}

}
