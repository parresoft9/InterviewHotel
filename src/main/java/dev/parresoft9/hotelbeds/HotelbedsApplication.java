package dev.parresoft9.hotelbeds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.hotelbeds.supplierintegrations.hackertest.detector")
public class HotelbedsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelbedsApplication.class, args);
	}

}
